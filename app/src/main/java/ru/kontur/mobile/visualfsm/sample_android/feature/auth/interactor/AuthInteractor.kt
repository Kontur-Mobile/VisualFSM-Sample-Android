package ru.kontur.mobile.visualfsm.sample_android.feature.auth.interactor

import ru.kontur.mobile.visualfsm.sample_android.feature.auth.data.AuthResult
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.data.RegistrationResult
import kotlinx.coroutines.delay

class AuthInteractor {
    var registeredMail: String = ""
    var registeredPassword: String = ""

    suspend fun check(mail: String, password: String): AuthResult {
        delay(3000)
        return if (registeredMail == mail && registeredPassword == password
            && mail.isNotBlank() && password.isNotBlank()) {
            AuthResult.SUCCESS
        } else {
            AuthResult.BAD_CREDENTIAL
        }
    }

    suspend fun register(mail: String, password: String): RegistrationResult {
        delay(3000)
        registeredMail = mail
        registeredPassword = password
        return RegistrationResult.SUCCESS
    }
}