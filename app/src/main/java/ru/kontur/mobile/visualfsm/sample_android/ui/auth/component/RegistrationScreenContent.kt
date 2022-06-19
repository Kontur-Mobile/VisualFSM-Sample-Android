package ru.kontur.mobile.visualfsm.sample_android.ui.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.RegistrationScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.common.CustomButton


@Composable
fun RegistrationScreenContent(
    data: RegistrationScreenData,
    onMailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onRegistrationClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        RegistrationText()
        Spacer(modifier = Modifier.height(64.dp))
        EmailInputField(
            modifier = Modifier.fillMaxWidth(),
            message = data.mail,
            onValueChange = onMailChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInputField(
            message = data.password,
            onValueChange = onPasswordChange,
            placeHolder = "Password",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInputField(
            message = data.repeatedPassword,
            onValueChange = onRepeatedPasswordChange,
            placeHolder = "Repeat password",
            modifier = Modifier.fillMaxWidth()
        )
        if (data.errorMessage != null && data.errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.errorMessage,
                color = MaterialTheme.colors.error
            )
        }
        Spacer(modifier = Modifier.height(96.dp))

        if (data.isRegistrationInProgress) {
            CircularProgressIndicator()
        } else {
            SignUpButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onRegistrationClick,
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

@Preview
@Composable
fun RegistrationScreenContentPreview() {
    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        RegistrationScreenContent(
            RegistrationScreenData(
                mail = "test@test.com",
                password = "",
                repeatedPassword = "",
                errorMessage = null,
                isRegistrationInProgress = false,
                isConfirmationRequested = false
            ),
            {},
            {},
            {},
            {})
    }
}