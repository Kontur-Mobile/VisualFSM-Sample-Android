package ru.kontur.mobile.visualfsm.sample_android.feature.auth.di

import android.os.Bundle
import org.koin.core.module.dsl.scopedOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMAsyncWorker
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature

object AuthStateModuleFactory {

    fun create(stateScopeQualifier: Qualifier, bundle: Bundle?) = module {
        scope(stateScopeQualifier) {
            scopedOf(::AuthFSMAsyncWorker)
            scoped {
                AuthFeature(
                    getSavedOrInitialAuthFSMState(bundle),
                    get()
                )
            }
        }
    }

    private fun getSavedOrInitialAuthFSMState(bundle: Bundle?): AuthFSMState {
        val initialState = AuthFSMState.Login("", "")

        return bundle?.getParcelable(AUTH_FSM_SAVED_STATE) ?: initialState
    }

    const val AUTH_FSM_SAVED_STATE = "auth_fsm_saved_state"
}
