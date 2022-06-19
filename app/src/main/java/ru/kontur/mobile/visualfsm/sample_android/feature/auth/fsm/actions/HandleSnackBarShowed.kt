package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState

class HandleSnackBarShowed() : AuthFSMAction() {

    inner class SnackBarShowed : Transition<AuthFSMState.Login, AuthFSMState.Login>(
        AuthFSMState.Login::class,
        AuthFSMState.Login::class
    ) {
        override fun transform(state: AuthFSMState.Login): AuthFSMState.Login {
            return state.copy(snackBarMessage = null)
        }
    }

    override fun getTransitions() = listOf(
        SnackBarShowed()
    )
}