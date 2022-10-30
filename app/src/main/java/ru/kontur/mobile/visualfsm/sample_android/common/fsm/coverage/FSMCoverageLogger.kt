package ru.kontur.mobile.visualfsm.sample_android.common.fsm.coverage

import ru.kontur.mobile.visualfsm.Action
import ru.kontur.mobile.visualfsm.State
import ru.kontur.mobile.visualfsm.Transition
import kotlin.reflect.KClass

abstract class FSMCoverageLogger {

    abstract fun <STATE : State> logTransition(
        baseStateClass: KClass<STATE>,
        actionClass: KClass<out Action<STATE>>,
        transitionClass: KClass<out Transition<STATE, STATE>>,
        useTransitionName: Boolean,
    )

}