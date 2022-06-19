package ru.kontur.mobile.visualfsm.sample_android.ui.auth

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import ru.kontur.mobile.visualfsm.sample_kmm.android.CustomInputField

@Composable
fun EmailInputField(
    modifier: Modifier = Modifier,
    message: String,
    onValueChange: (String) -> Unit,
) {
    CustomInputField(
        message = message,
        onValueChange = onValueChange,
        placeHolder = "Email",
        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "input email") },
        modifier = modifier
    )
}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    message: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
) {
    CustomInputField(
        message = message,
        onValueChange = onValueChange,
        placeHolder = placeHolder,
        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "input password") },
        modifier = modifier,
        visualTransformation = PasswordVisualTransformation()
    )
}