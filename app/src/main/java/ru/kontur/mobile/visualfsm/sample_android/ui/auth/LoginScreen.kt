package ru.kontur.mobile.visualfsm.sample_android.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kontur.mobile.visualfsm.sample_android.DependencyManager
import ru.kontur.mobile.visualfsm.sample_kmm.android.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature

@Composable
fun LoginScreen(
    state: AuthFSMState.Login,
) {
    ContentLogin(
        mail = state.mail,
        password = state.password,
        errorMessage = state.errorMessage,
        isAuthenticationInProgress = false
    )
}

@Composable
fun LoginScreen(
    state: AuthFSMState.AsyncWorkState.Authenticating,
) {
    ContentLogin(
        mail = state.mail,
        password = state.password,
        errorMessage = null,
        isAuthenticationInProgress = true,
    )
}

@Composable
private fun ContentLogin(
    mail: String,
    password: String,
    errorMessage: String?,
    isAuthenticationInProgress: Boolean,
) {
    val authFeature: AuthFeature = DependencyManager.getAuthFeature()

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                LoginText()

                Spacer(modifier = Modifier.height(64.dp))
                EmailInputField(
                    modifier = Modifier.fillMaxWidth(),
                    message = mail,
                    onValueChange = {
                        authFeature.handleChangeLoginData(
                            mail = it,
                            password = password,
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInputField(
                    placeHolder = "Password",
                    modifier = Modifier.fillMaxWidth(),
                    message = password,
                    onValueChange = {
                        authFeature.handleChangeLoginData(
                            mail = mail,
                            password = it,
                        )
                    }
                )

                if (errorMessage != null && errorMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colors.error
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                SignUpText(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    authFeature.toRegistration()
                }
                Spacer(modifier = Modifier.height(32.dp))

                if (isAuthenticationInProgress) {
                    CircularProgressIndicator()
                } else {
                    SignInButton(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        authFeature.startAuthenticating()
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginText(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Login",
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
private fun SignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    CustomButton(
        onClick = onClick,
        text = "Sign in",
        modifier = modifier
    )
}

@Composable
private fun SignUpText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        CustomTextButton(
            text = "Sign up",
            onClick = onClick,
        )
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(AuthFSMState.Login("test@mail.com", ""))
}