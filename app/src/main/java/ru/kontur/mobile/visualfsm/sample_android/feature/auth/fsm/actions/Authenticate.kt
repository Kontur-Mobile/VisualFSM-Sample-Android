package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*

class Authenticate : AuthFSMAction() {
    inner class AuthenticationStart : Transition<Login, AsyncWorkState.Authenticating>(
        Login::class,
        AsyncWorkState.Authenticating::class
    ) {
        override fun transform(state: Login): AsyncWorkState.Authenticating {
            return AsyncWorkState.Authenticating(state.mail, state.password)
        }
    }

    override fun getTransitions() = listOf(
        AuthenticationStart(),
    )
}