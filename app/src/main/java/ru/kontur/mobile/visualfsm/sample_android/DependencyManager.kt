package ru.kontur.mobile.visualfsm.sample_android

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature

class DependencyManager {
    companion object {
        private val authFeature = AuthFeature(
            initialState = AuthFSMState.Login("", "")
        )

        fun getAuthFeature(): AuthFeature {
            return authFeature
        }
    }
}