package ru.kontur.mobile.visualfsm.sample_android.ui.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFeature
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.component.UserAuthorizedScreenContent
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.UserAuthorizedScreenData

@Composable
fun UserAuthorizedScreen(
    data: UserAuthorizedScreenData,
    authFeature: AuthFeature
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            UserAuthorizedScreenContent(
                data,
                onLogoutClick = { authFeature.logout() }
            )
        }
    }
}