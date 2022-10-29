package ru.kontur.mobile.visualfsm.sample_android.tests

import org.junit.Test
import ru.kontur.mobile.visualfsm.sample_android.screen.ConfirmDialogScreen
import ru.kontur.mobile.visualfsm.sample_android.screen.LoginScreen
import ru.kontur.mobile.visualfsm.sample_android.screen.RegistrationScreen
import ru.kontur.mobile.visualfsm.sample_android.screen.UserAuthorizedScreen

class SignUpTest : BaseTestCase() {

    @Test
    fun test() = run {
        step("Press \"sign up\"") {
            onComposeScreen<LoginScreen> {
                signUpText.performClick()
            }
        }
        step("Register new user") {
            onComposeScreen<RegistrationScreen> {
                emailInput.performTextInput("TestUser")
                passwordInput.performTextInput("testuserpassword")
                passwordRepeatInput.performTextInput("testuserpassword")
                signUpButton.performClick()
            }
            onComposeScreen<ConfirmDialogScreen> {
                okButton.performClick()
            }
        }
        step("Check that email prefilled") {
            onComposeScreen<LoginScreen> {
                emailInput.assertTextEquals("TestUser")
            }
        }
        step("Sign in") {
            onComposeScreen<LoginScreen> {
                signInButton.performClick()
            }
        }
        step("Check that welcome a user named \"TestUser\"") {
            onComposeScreen<UserAuthorizedScreen> {
                emailText.assertTextEquals("TestUser")
            }
        }
    }
}
