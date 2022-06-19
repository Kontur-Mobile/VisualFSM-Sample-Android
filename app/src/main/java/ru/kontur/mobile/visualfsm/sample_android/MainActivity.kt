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
import ru.kontur.mobile.visualfsm.sample_android.ui.theme.VisualFSMSampleAndroidTheme
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.ScreenDataMapper
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.LoginScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.RegistrationScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.UserAuthorizedScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen.LoginScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen.RegistrationScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen.UserAuthorizedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisualFSMSampleAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AuthFlow()
                }
            }
        }
    }
}

@Composable
private fun AuthFlow() {
    val authFeature = DependencyManager.getAuthFeature()

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
