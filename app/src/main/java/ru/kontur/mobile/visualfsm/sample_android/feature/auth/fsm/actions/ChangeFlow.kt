package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.data.UserFlow
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

class ChangeFlow(val newFlow: UserFlow) : AuthFSMAction() {

    @Edge("ToLogin")
    inner class RegisterToLogin : Transition<Registration, Login>(
        Registration::class,
        Login::class
    ) {
        override fun transform(state: Registration): Login {
            return Login(state.mail, "")
        }
    }


    @Edge("ToRegistration")
    inner class LoginToRegistration : Transition<Login, Registration>(
        Login::class,
        Registration::class
    ) {
        override fun transform(state: Login): Registration {
            return Registration(state.mail, "", "")
        }
    }

    override fun getTransitions() = listOf(
        RegisterToLogin(),
        LoginToRegistration(),
    )
}