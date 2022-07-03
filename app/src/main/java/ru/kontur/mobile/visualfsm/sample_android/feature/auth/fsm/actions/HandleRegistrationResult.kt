package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.data.RegistrationResult

class HandleRegistrationResult(val result: RegistrationResult) : AuthFSMAction() {

    inner class Success : Transition<AsyncWorkState.Registering, Login>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.SUCCESS
        }

        override fun transform(state: AsyncWorkState.Registering): Login {
            return Login(
                mail = state.mail,
                password = state.password,
                snackBarMessage = "${state.mail} registered"
            )
        }
    }

    inner class BadCredential : Transition<AsyncWorkState.Registering, Registration>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.USER_ALREADY_REGISTERED
        }

        override fun transform(state: AsyncWorkState.Registering): Registration {
            return Registration(
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = "User already registered"
            )
        }
    }

    inner class ConnectionFailed : Transition<AsyncWorkState.Registering, Registration>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.NO_INTERNET
        }

        override fun transform(state: AsyncWorkState.Registering): Registration {
            return Registration(
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = "No internet"
            )
        }
    }
}