package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState

class HandleChangeRegistrationData(
    val mail: String,
    val password: String,
    val repeatPassword: String,
) : AuthFSMAction() {

    inner class ChangeRegistrationData : Transition<AuthFSMState.Registration, AuthFSMState.Registration>() {
        override fun transform(state: AuthFSMState.Registration): AuthFSMState.Registration {
            return AuthFSMState.Registration(mail, password, repeatPassword)
        }
    }
}