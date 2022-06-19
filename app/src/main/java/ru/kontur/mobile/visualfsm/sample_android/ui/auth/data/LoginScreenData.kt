package ru.kontur.mobile.visualfsm.sample_android.ui.auth.data

data class LoginScreenData(
    val mail: String,
    val password: String,
    val errorMessage: String?,
    val isAuthenticationInProgress: Boolean,
    val snackBarMessage: String?
) : AuthScreenData()