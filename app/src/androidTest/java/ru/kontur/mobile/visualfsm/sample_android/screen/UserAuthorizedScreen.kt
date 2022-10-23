package ru.kontur.mobile.visualfsm.sample_android.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import ru.kontur.mobile.visualfsm.sample_android.resources.TestTag

class UserAuthorizedScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<UserAuthorizedScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(TestTag.UserAuthorizedScreen.container) },
) {
    val emailText = child<KNode> { hasTestTag(TestTag.UserAuthorizedScreen.email_text) }
    val logOutButton = child<KNode> { hasTestTag(TestTag.UserAuthorizedScreen.log_out_button) }
}
