package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*
import ru.kontur.mobile.visualfsm.Transition

class Logout : AuthFSMAction() {
    inner class Logout : Transition<UserAuthorized, Login>(
        UserAuthorized::class,
        Login::class
    ) {
        override fun transform(state: UserAuthorized) = Login("", "")
    }

    override fun getTransitions() = listOf(
        Logout(),
    )
}