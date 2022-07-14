package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*

class StartRegistration : AuthFSMAction() {
    inner class RegistrationStart : Transition<Registration, ConfirmationRequested>() {
        override fun predicate(state: Registration): Boolean {
            return state.password == state.repeatedPassword && state.password.isNotBlank()
        }

        override fun transform(state: Registration): ConfirmationRequested {
            return ConfirmationRequested(state.mail, state.password)
        }
    }

    inner class ValidationFailed : Transition<Registration, Registration>() {
        override fun predicate(state: Registration): Boolean {
            return state.password != state.repeatedPassword || state.password.isBlank()
        }

        override fun transform(state: Registration): Registration {
            return Registration(
                state.mail,
                state.password,
                state.repeatedPassword,
                "Password and repeated password must be equals and not empty"
            )
        }
    }
}