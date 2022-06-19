package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState

class HandleChangeLoginData(
    val mail: String,
    val password: String,
): AuthFSMAction() {

    inner class ChangeLoginData: Transition<AuthFSMState.Login, AuthFSMState.Login>(
        AuthFSMState.Login::class,
        AuthFSMState.Login::class
    ) {
        override fun transform(state: AuthFSMState.Login): AuthFSMState.Login {
            return AuthFSMState.Login(mail, password)
        }
    }

    override fun getTransitions() = listOf(
        ChangeLoginData()
    )
}