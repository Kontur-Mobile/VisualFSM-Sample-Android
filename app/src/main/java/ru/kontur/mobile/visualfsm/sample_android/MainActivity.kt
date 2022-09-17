package ru.kontur.mobile.visualfsm.sample_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.android.ext.android.get
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.createActivityRetainedScope
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.scopedOf
import org.koin.core.scope.Scope
import org.koin.dsl.module
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMAsyncWorker
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.ScreenDataMapper
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.LoginScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.RegistrationScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.UserAuthorizedScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen.LoginScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen.RegistrationScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen.UserAuthorizedScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.theme.VisualFSMSampleAndroidTheme

class MainActivity : ComponentActivity(), AndroidScopeComponent {
    override var scope: Scope? = null

    private lateinit var authFeature: AuthFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createActivityRetainedScope()

        authFeature = try {
            get()
        } catch (e: Exception) {
            loadKoinModules(
                module {
                    scope<MainActivity> {
                        scopedOf(::AuthFSMAsyncWorker)
                        scoped { AuthFeature(getSavedOrInitialAuthFSMState(savedInstanceState), get()) }
                    }
                })
            get()
        }

        setContent {
            VisualFSMSampleAndroidTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AuthFlow(authFeature)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            scope?.closed
            scope = null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(AUTH_FSM_SAVED_STATE, authFeature.getCurrentState())
        super.onSaveInstanceState(outState)
    }

    private fun getSavedOrInitialAuthFSMState(savedInstanceState: Bundle?): AuthFSMState {
        val initialState = AuthFSMState.Login("", "")

        return if (savedInstanceState != null) {
            val parcelableState = savedInstanceState.getParcelable<AuthFSMState>(AUTH_FSM_SAVED_STATE)
            parcelableState ?: initialState
        } else {
            initialState
        }
    }

    companion object {
        private const val AUTH_FSM_SAVED_STATE = "auth_fsm_saved_state"
    }
}

@Composable
private fun AuthFlow(authFeature: AuthFeature) {
    val state = authFeature.observeState()
        .collectAsState(
            initial = authFeature.getCurrentState()
        ).value

    when (val data = ScreenDataMapper.map(state)) {
        is LoginScreenData -> LoginScreen(data, authFeature)
        is RegistrationScreenData -> RegistrationScreen(data, authFeature)
        is UserAuthorizedScreenData -> UserAuthorizedScreen(data, authFeature)
    }
}
