package ru.kontur.mobile.visualfsm.sample_android.common.fsm

import androidx.annotation.CallSuper
import ru.kontur.mobile.visualfsm.Action
import ru.kontur.mobile.visualfsm.State
import ru.kontur.mobile.visualfsm.Transition
import ru.kontur.mobile.visualfsm.TransitionCallbacks
import ru.kontur.mobile.visualfsm.sample_android.common.fsm.coverage.FSMCoverageLogger
import kotlin.reflect.KClass

abstract class BaseTransitionCallbacks<STATE : State>(
    private val fsmCoverageLogger: FSMCoverageLogger,
    private val baseStateClass: KClass<STATE>,
    private val useTransitionName: Boolean = true,
) : TransitionCallbacks<STATE> {

    @CallSuper
    override fun onTransitionSelected(action: Action<STATE>, transition: Transition<STATE, STATE>, currentState: STATE) {
        fsmCoverageLogger.logTransition(baseStateClass, action::class, transition::class, useTransitionName)
    }
}