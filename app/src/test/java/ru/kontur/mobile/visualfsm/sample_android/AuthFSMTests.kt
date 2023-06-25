package ru.kontur.mobile.visualfsm.sample_android

import org.junit.Test

import org.junit.Assert.*
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.actions.AuthFSMAction
import ru.kontur.mobile.visualfsm.tools.VisualFSM
import ru.kontur.mobile.visualfsm.tools.graphviz.preset.DotAttributesDefaultPreset

class AuthFSMTests {

    @Test
    fun generateDigraph() {
        println(
            VisualFSM.generateDigraph(
                baseAction = AuthFSMAction::class,
                baseState = AuthFSMState::class,
                initialState = AuthFSMState.Login::class,
                attributes = DotAttributesDefaultPreset(AuthFSMState.AsyncWorkState::class),
            )
        )
        assertTrue(true)
    }

    @Test
    fun allStatesReachableTest() {
        val notReachableStates = VisualFSM.getUnreachableStates(
            baseAction = AuthFSMAction::class,
            baseState = AuthFSMState::class,
            initialState = AuthFSMState.Login::class,
        )

        assertTrue(
            "FSM have unreachable states: ${notReachableStates.joinToString(", ")}",
            notReachableStates.isEmpty()
        )
    }

    @Test
    fun noFinalStateTest() {
        val finalStates = VisualFSM.getFinalStates(
            baseAction = AuthFSMAction::class,
            baseState = AuthFSMState::class,
        )

        assertTrue(
            "FSM have not correct final states: ${finalStates.joinToString(", ")}",
            finalStates.isEmpty()
        )
    }
}