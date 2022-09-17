package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.kontur.mobile.visualfsm.State

@Parcelize
sealed class AuthFSMState : State, Parcelable {
    data class Login(
        val mail: String,
        val password: String,
        val errorMessage: String? = null,
        val snackBarMessage: String? = null,
    ) : AuthFSMState()

    data class Registration(
        val mail: String,
        val password: String,
        val repeatedPassword: String,
        val errorMessage: String? = null
    ) : AuthFSMState()

    data class ConfirmationRequested(
        val mail: String,
        val password: String
    ) : AuthFSMState()

    @Parcelize
    sealed class AsyncWorkState : AuthFSMState() {
        data class Authenticating(
            val mail: String,
            val password: String
        ) : AsyncWorkState()

        data class Registering(
            val mail: String,
            val password: String
        ) : AsyncWorkState()
    }

    data class UserAuthorized(val mail: String) : AuthFSMState()
}