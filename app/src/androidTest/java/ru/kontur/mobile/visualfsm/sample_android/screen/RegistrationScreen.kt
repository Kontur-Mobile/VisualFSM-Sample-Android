package ru.kontur.mobile.visualfsm.sample_android.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import ru.kontur.mobile.visualfsm.sample_android.resources.TestTag

class RegistrationScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<RegistrationScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(TestTag.RegistrationScreen.container) },
) {
    val emailInput = child<KNode> { hasTestTag(TestTag.RegistrationScreen.email_input_field) }
    val passwordInput = child<KNode> { hasTestTag(TestTag.RegistrationScreen.password_input_field) }
    val passwordRepeatInput = child<KNode> { hasTestTag(TestTag.RegistrationScreen.password_repeat_input_field) }
    val errorText = child<KNode> { hasTestTag(TestTag.RegistrationScreen.error_text) }
    val signUpButton = child<KNode> { hasTestTag(TestTag.RegistrationScreen.sign_up_button) }
}
