package ru.kontur.mobile.visualfsm.sample_android.ui.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.LoginScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.common.CustomButton
import ru.kontur.mobile.visualfsm.sample_android.ui.common.CustomTextButton

@Composable
fun LoginScreenContent(
    data: LoginScreenData,
    onMailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
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
            message = data.mail,
            onValueChange = onMailChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInputField(
            placeHolder = "Password",
            modifier = Modifier.fillMaxWidth(),
            message = data.password,
            onValueChange = onPasswordChange
        )

        if (data.errorMessage != null && data.errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.errorMessage,
                color = MaterialTheme.colors.error
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        SignUpText(
            modifier = Modifier.fillMaxWidth(),
            onSignUpClick
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (data.isAuthenticationInProgress) {
            CircularProgressIndicator()
        } else {
            SignInButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onSignInClick
            )
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
fun LoginScreenContentPreview() {
    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        LoginScreenContent(
            LoginScreenData(
                mail = "test@test.com",
                password = "",
                errorMessage = null,
                isAuthenticationInProgress = false,
                snackBarMessage = null,
            ),
            {},
            {},
            {},
            {})
    }
}