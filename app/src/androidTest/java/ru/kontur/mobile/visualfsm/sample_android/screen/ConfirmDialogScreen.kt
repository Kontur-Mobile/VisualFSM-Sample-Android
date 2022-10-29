package ru.kontur.mobile.visualfsm.sample_android.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import ru.kontur.mobile.visualfsm.sample_android.resources.TestTag

class ConfirmDialogScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<ConfirmDialogScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(TestTag.ConfirmDialogScreen.container) },
) {
    val okButton = child<KNode> { hasTestTag(TestTag.ConfirmDialogScreen.ok_button) }
    val cancelButton = child<KNode> { hasTestTag(TestTag.ConfirmDialogScreen.cancel_button) }
}
