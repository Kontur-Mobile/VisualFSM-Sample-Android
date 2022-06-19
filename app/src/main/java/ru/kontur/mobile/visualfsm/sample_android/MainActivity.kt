package ru.kontur.mobile.visualfsm.sample_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.ui.theme.VisualFSMSampleAndroidTheme
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.LoginScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.RegistrationScreen
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.UserAuthorizedScreen

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
                    AuthScreen()
                }
            }
        }
    }
}

@Composable
private fun AuthScreen() {
    val demoAuthFeature = DependencyManager.getAuthFeature()

    val state = demoAuthFeature.observeState()
        .collectAsState(
            initial = demoAuthFeature.getCurrentState()
        ).value

    when (state) {
        is AuthFSMState.AsyncWorkState.Authenticating -> LoginScreen(state)
        is AuthFSMState.AsyncWorkState.Registering -> RegistrationScreen(state)
        is AuthFSMState.ConfirmationRequested -> RegistrationScreen(state)
        is AuthFSMState.Login -> LoginScreen(state)
        is AuthFSMState.Registration -> RegistrationScreen(state)
        is AuthFSMState.UserAuthorized -> UserAuthorizedScreen(state)
    }
}
