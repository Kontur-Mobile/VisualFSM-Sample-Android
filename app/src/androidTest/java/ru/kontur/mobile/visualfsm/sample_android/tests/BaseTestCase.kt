package ru.kontur.mobile.visualfsm.sample_android.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.runner.RunWith
import ru.kontur.mobile.visualfsm.sample_android.MainActivity

@RunWith(AndroidJUnit4::class)
abstract class BaseTestCase : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    inline fun <reified T : ComposeScreen<T>> onComposeScreen(noinline function: T.() -> Unit): T {
        return ComposeScreen.onComposeScreen(composeTestRule, function)
    }
}
