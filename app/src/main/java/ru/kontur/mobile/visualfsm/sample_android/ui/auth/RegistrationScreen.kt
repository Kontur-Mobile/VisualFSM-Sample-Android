package ru.kontur.mobile.visualfsm.sample_android.ui.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kontur.mobile.visualfsm.sample_android.DependencyManager
import ru.kontur.mobile.visualfsm.sample_kmm.android.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature

@Composable
fun RegistrationScreen(
    state: AuthFSMState.Registration,
) {
    ContentRegistration(
        mail = state.mail,
        password = state.password,
        repeatedPassword = state.repeatedPassword,
        errorMessage = state.errorMessage,
        isRegistrationInProgress = false,
    )
}

@Composable
fun RegistrationScreen(
    state: AuthFSMState.AsyncWorkState.Registering,
) {
    ContentRegistration(
        mail = state.mail,
        password = state.password,
        repeatedPassword = state.password,
        errorMessage = null,
        isRegistrationInProgress = true
    )
}

@Composable
fun RegistrationScreen(
    state: AuthFSMState.ConfirmationRequested,
) {
    ContentRegistration(
        mail = state.mail,
        password = state.password,
        repeatedPassword = state.password,
        errorMessage = null,
        isRegistrationInProgress = false
    )
    ConfirmRegistrationDialog()
}

@Composable
private fun ContentRegistration(
    mail: String,
    password: String,
    repeatedPassword: String,
    errorMessage: String?,
    isRegistrationInProgress: Boolean,
) {
    val authFeature: AuthFeature = DependencyManager.getAuthFeature()

    BackHandler(enabled = true) {
        authFeature.toLogin()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
            ) {
                ButtonArrowBack {
                    authFeature.toLogin()
                }
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            RegistrationText()
            Spacer(modifier = Modifier.height(64.dp))
            EmailInputField(
                modifier = Modifier.fillMaxWidth(),
                message = mail,
                onValueChange = { authFeature.handleChangeRegistrationData(
                    mail = it,
                    password = password,
                    repeatPassword = repeatedPassword
                )}
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInputField(
                message = password,
                onValueChange = { authFeature.handleChangeRegistrationData(
                    mail = mail,
                    password = it,
                    repeatPassword = repeatedPassword
                ) },
                placeHolder = "Password",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInputField(
                message = repeatedPassword,
                onValueChange = { authFeature.handleChangeRegistrationData(
                    mail = mail,
                    password = password,
                    repeatPassword = it
                ) },
                placeHolder = "Repeat password",
                modifier = Modifier.fillMaxWidth()
            )
            if (errorMessage != null && errorMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colors.error
                )
            }
            Spacer(modifier = Modifier.height(96.dp))

            if (isRegistrationInProgress) {
                CircularProgressIndicator()
            } else {
                SignUpButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { authFeature.startRegistration() },
                )
            }
        }
    }
}

@Composable
fun ConfirmRegistrationDialog() {
    val authFeature = DependencyManager.getAuthFeature()

    CustomConfirmDialog(
        title = "Confirm registration",
        description = "Continue with current data?",
        onDismiss = { authFeature.declineRegistrationData() },
        onConfirm = { authFeature.confirmRegistrationData() },
        onCancel = { authFeature.declineRegistrationData() },
    )
}

@Composable
private fun ButtonArrowBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "return previous screen"
        )
    }
}

@Composable
private fun RegistrationText(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Registration",
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
private fun SignUpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CustomButton(
        onClick = onClick,
        text = "Sign up",
        modifier = modifier
    )
}