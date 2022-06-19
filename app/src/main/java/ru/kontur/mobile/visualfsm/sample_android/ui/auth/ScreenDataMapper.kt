package ru.kontur.mobile.visualfsm.sample_android.ui.auth

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.AuthScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.LoginScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.RegistrationScreenData
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.UserAuthorizedScreenData

object ScreenDataMapper {
    fun map(state: AuthFSMState): AuthScreenData {
        return when (state) {
            is AuthFSMState.Login -> LoginScreenData(
                mail = state.mail,
                password = state.password,
                errorMessage = state.errorMessage,
                isAuthenticationInProgress = false,
                snackBarMessage = state.snackBarMessage
            )
            is AuthFSMState.AsyncWorkState.Authenticating -> LoginScreenData(
                mail = state.mail,
                password = state.password,
                errorMessage = null,
                isAuthenticationInProgress = true,
                snackBarMessage = null
            )
            is AuthFSMState.Registration -> RegistrationScreenData(
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.repeatedPassword,
                errorMessage = state.errorMessage,
                isRegistrationInProgress = false,
                isConfirmationRequested = false
            )
            is AuthFSMState.AsyncWorkState.Registering -> RegistrationScreenData(
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = null,
                isRegistrationInProgress = true,
                isConfirmationRequested = false
            )
            is AuthFSMState.ConfirmationRequested -> RegistrationScreenData(
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = null,
                isRegistrationInProgress = false,
                isConfirmationRequested = true
            )
            is AuthFSMState.UserAuthorized -> UserAuthorizedScreenData(
                mail = state.mail
            )
        }
    }
}