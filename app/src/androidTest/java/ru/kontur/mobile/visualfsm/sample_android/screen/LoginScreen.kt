package ru.kontur.mobile.visualfsm.sample_android.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import ru.kontur.mobile.visualfsm.sample_android.resources.TestTag

class LoginScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<LoginScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(TestTag.LoginScreen.container) },
) {
    val emailInput = child<KNode> { hasTestTag(TestTag.LoginScreen.email_input_field) }
    val passwordInput = child<KNode> { hasTestTag(TestTag.LoginScreen.password_input_field) }
    val errorText = child<KNode> { hasTestTag(TestTag.LoginScreen.error_text) }
    val signUpText = child<KNode> { hasTestTag(TestTag.LoginScreen.sign_up_text) }
    val signInButton = child<KNode> { hasTestTag(TestTag.LoginScreen.sign_in_button) }
}
