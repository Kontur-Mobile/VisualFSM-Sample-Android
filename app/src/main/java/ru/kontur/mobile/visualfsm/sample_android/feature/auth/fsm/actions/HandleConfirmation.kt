package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*

class HandleConfirmation(val confirmed: Boolean) : AuthFSMAction() {
    inner class Confirm : Transition<ConfirmationRequested, AsyncWorkState.Registering>() {
        override fun predicate(state: ConfirmationRequested): Boolean {
            return confirmed
        }

        override fun transform(state: ConfirmationRequested): AsyncWorkState.Registering {
            return AsyncWorkState.Registering(state.mail, state.password)
        }
    }

    inner class Cancel : Transition<ConfirmationRequested, Registration>() {
        override fun predicate(state: ConfirmationRequested): Boolean {
            return !confirmed
        }

        override fun transform(state: ConfirmationRequested): Registration {
            return Registration(state.mail, state.password, state.password)
        }
    }
}