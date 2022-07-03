package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.data.UserFlow
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

class ChangeFlow(val newFlow: UserFlow) : AuthFSMAction() {

    @Edge("ToLogin")
    inner class RegisterToLogin : Transition<Registration, Login>() {
        override fun transform(state: Registration): Login {
            return Login(state.mail, "")
        }
    }

    @Edge("ToRegistration")
    inner class LoginToRegistration : Transition<Login, Registration>() {
        override fun transform(state: Login): Registration {
            return Registration(state.mail, "", "")
        }
    }
}