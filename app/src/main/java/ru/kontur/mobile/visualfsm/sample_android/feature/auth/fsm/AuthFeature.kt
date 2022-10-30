package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm

import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory
import ru.kontur.mobile.visualfsm.providers.GeneratedTransitionsFactoryProvider.provideTransitionsFactory
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.data.UserFlow
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions.*

@GenerateTransitionsFactory
class AuthFeature(
    initialState: AuthFSMState,
    asyncWorker: AuthFSMAsyncWorker,
    transitionCallbacks: AuthTransitionCallback,
) : Feature<AuthFSMState, AuthFSMAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionCallbacks = transitionCallbacks,
    transitionsFactory = provideTransitionsFactory(),
) {

    fun toRegistration() {
        proceed(ChangeFlow(UserFlow.REGISTRATION))
    }

    fun toLogin() {
        proceed(ChangeFlow(UserFlow.LOGIN))
    }

    fun logout() {
        proceed(Logout())
    }

    fun confirmRegistrationData() {
        proceed(HandleConfirmation(true))
    }

    fun declineRegistrationData() {
        proceed(HandleConfirmation(false))
    }

    fun startAuthenticating() {
        proceed(Authenticate())
    }

    fun startRegistration() {
        proceed(StartRegistration())
    }

    fun handleChangeRegistrationData(mail: String, password: String, repeatPassword: String) {
        proceed(HandleChangeRegistrationData(mail, password, repeatPassword))
    }

    fun handleChangeLoginData(mail: String, password: String) {
        proceed(HandleChangeLoginData(mail, password))
    }

    fun handleSnackBarShowed() {
        proceed(HandleSnackBarShowed())
    }
}