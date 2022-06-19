package ru.kontur.mobile.visualfsm.sample_android.ui.auth.data

data class RegistrationScreenData(
    val mail: String,
    val password: String,
    val repeatedPassword: String,
    val errorMessage: String?,
    val isRegistrationInProgress: Boolean,
    val isConfirmationRequested: Boolean,
) : AuthScreenData()