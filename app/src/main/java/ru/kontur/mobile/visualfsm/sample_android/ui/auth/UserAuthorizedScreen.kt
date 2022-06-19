package ru.kontur.mobile.visualfsm.sample_android.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kontur.mobile.visualfsm.sample_android.DependencyManager
import ru.kontur.mobile.visualfsm.sample_android.feature.auth.fsm.AuthFSMState

@Composable
fun UserAuthorizedScreen(state: AuthFSMState.UserAuthorized) {
    val authFeature = DependencyManager.getAuthFeature()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(128.dp))
            Text(
                text = "Welcome!",
                fontSize = 26.sp,
            )
            Text(
                text = state.mail,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextButton(
                onClick = { authFeature.logout() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Log out",
                    fontSize = 18.sp
                )
            }
        }
    }
}