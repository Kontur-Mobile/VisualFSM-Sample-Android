package ru.kontur.mobile.visualfsm.sample_android.resources

object TestTag {
    object LoginScreen {
        const val container = "login_screen_container"
        const val email_input_field = "login_screen_email_input_field"
        const val password_input_field = "login_screen_password_input_field"
        const val error_text = "login_screen_error_text"
        const val sign_up_text = "login_screen_sign_up_text"
        const val sign_in_button = "login_screen_sign_in_button"
    }

    object RegistrationScreen {
        const val container = "registration_screen_container"
        const val email_input_field = "registration_screen_email_input_field"
        const val password_input_field = "registration_screen_password_input_field"
        const val password_repeat_input_field = "registration_screen_password_repeat_input_field"
        const val error_text = "registration_screen_error_text"
        const val sign_up_button = "registration_screen_sign_up_button"
    }

    object UserAuthorizedScreen {
        const val container = "user_authorized_screen_container"
        const val email_text = "user_authorized_screen_email_text"
        const val log_out_button = "user_authorized_screen_log_out_button"
    }

    object ConfirmDialogScreen {
        const val container = "confirm_dialog_screen_container"
        const val ok_button = "confirm_dialog_screen_ok_button"
        const val cancel_button = "confirm_dialog_screen_cancel_button"
    }
}