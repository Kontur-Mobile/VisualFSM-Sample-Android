package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions.AuthFSMAction
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.interactor.AuthInteractor
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions.HandleAuthResult
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions.HandleRegistrationResult

class AuthFSMAsyncWorker(private val authInteractor: AuthInteractor) :
    AsyncWorker<AuthFSMState, AuthFSMAction>() {

    override fun onNextState(state: AuthFSMState): AsyncWorkerTask<AuthFSMState> {
        return when (state) {
            is AsyncWorkState.Authenticating -> {
                AsyncWorkerTask.ExecuteAndCancelExist(state) {
                    val result = authInteractor.check(state.mail, state.password)
                    proceed(HandleAuthResult(result))
                }
            }
            is AsyncWorkState.Registering -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    val result = authInteractor.register(state.mail, state.password)
                    proceed(HandleRegistrationResult(result))
                }
            }
            else -> AsyncWorkerTask.Cancel()
        }
    }
}