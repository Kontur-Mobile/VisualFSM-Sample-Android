package ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.component.RegistrationScreenContent
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.RegistrationScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.common.ConfirmDialog

@Composable
fun RegistrationScreen(
    data: RegistrationScreenData,
    authFeature: AuthFeature
) {
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
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(padding)
        ) {
            RegistrationScreenContent(data = data,
                onMailChange = { mail ->
                    authFeature.handleChangeRegistrationData(
                        mail,
                        data.password, data.repeatedPassword
                    )
                },
                onPasswordChange = { password ->
                    authFeature.handleChangeRegistrationData(
                        data.mail,
                        password, data.repeatedPassword
                    )
                },
                onRepeatedPasswordChange = { repeatedPassword ->
                    authFeature.handleChangeRegistrationData(
                        data.mail,
                        data.password,
                        repeatedPassword
                    )
                },
                onRegistrationClick = { authFeature.startRegistration() })
        }
        if (data.isConfirmationRequested) {
            ConfirmDialog(
                title = "Confirm registration",
                description = "Continue with current data?",
                onDismiss = { authFeature.declineRegistrationData() },
                onConfirm = { authFeature.confirmRegistrationData() },
                onCancel = { authFeature.declineRegistrationData() },
            )
        }
    }
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