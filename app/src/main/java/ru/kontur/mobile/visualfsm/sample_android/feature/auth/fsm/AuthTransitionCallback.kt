package ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm

import ru.kontur.mobile.visualfsm.Action
import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.sample_android.common.fsm.BaseTransitionCallbacks
import ru.kontur.mobile.visualfsm.sample_android.common.fsm.coverage.FSMCoverageLogger

class AuthTransitionCallback(coverageLogger: FSMCoverageLogger) : BaseTransitionCallbacks<AuthFSMState>(coverageLogger, AuthFSMState::class) {
    override fun onActionLaunched(action: Action<AuthFSMState>, currentState: AuthFSMState) = Unit

    override fun onMultipleTransitionError(action: Action<AuthFSMState>, currentState: AuthFSMState) = Unit

    override fun onNewStateReduced(
        action: Action<AuthFSMState>,
        transition: Transition<AuthFSMState, AuthFSMState>,
        oldState: AuthFSMState,
        newState: AuthFSMState,
    ) = Unit

    override fun onNoTransitionError(action: Action<AuthFSMState>, currentState: AuthFSMState) = Unit
}