package ru.kontur.mobile.visualfsm.sample_android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.android.ext.android.inject
import org.koin.core.module.dsl.scopedOf
import org.koin.core.qualifier.Qualifier
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

class MainActivity : BaseActivity() {

    private val authFeature: AuthFeature by inject()

    override val stateScopeModule = { stateScopeQualifier: Qualifier, bundle: Bundle? ->
        module {
            scope(stateScopeQualifier) {
                scopedOf(::AuthFSMAsyncWorker)
                scoped {
                    AuthFeature(
                        getSavedOrInitialAuthFSMState(bundle),
                        get()
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(AUTH_FSM_SAVED_STATE, authFeature.getCurrentState())
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val AUTH_FSM_SAVED_STATE = "auth_fsm_saved_state"

        private fun getSavedOrInitialAuthFSMState(bundle: Bundle?): AuthFSMState {
            val initialState = AuthFSMState.Login("", "")

            return bundle?.getParcelable(AUTH_FSM_SAVED_STATE) ?: initialState
        }
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
