package ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.component.LoginScreenContent
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.LoginScreenData

@Composable
fun LoginScreen(
    data: LoginScreenData,
    authFeature: AuthFeature
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LoginScreenContent(data = data,
                onMailChange = { mail -> authFeature.handleChangeLoginData(mail, data.password) },
                onPasswordChange = { password -> authFeature.handleChangeLoginData(data.mail, password) },
                onSignInClick = { authFeature.startAuthenticating() },
                onSignUpClick = { authFeature.toRegistration() })
        }
    }
}