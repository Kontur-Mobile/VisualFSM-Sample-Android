package ru.kontur.mobile.visualfsm.sample_android.ui.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kontur.mobile.visualfsm.sample_android.resources.TestTag
import ru.kontur.mobile.visualfsm.sample_android.ui.auth.data.UserAuthorizedScreenData

@Composable
fun UserAuthorizedScreenContent(
    state: UserAuthorizedScreenData,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .semantics { testTag = TestTag.UserAuthorizedScreen.container },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            text = "Welcome!",
            fontSize = 26.sp,
        )
        Text(
            modifier = Modifier.semantics { testTag = TestTag.UserAuthorizedScreen.email_text },
            text = state.mail,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { testTag = TestTag.UserAuthorizedScreen.log_out_button },
        ) {
            Text(
                text = "Log out",
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun UserAuthorizedScreenContentPreview() {
    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        UserAuthorizedScreenContent(
            UserAuthorizedScreenData(
                mail = "test@test.com"
            )
        ) {}
    }
}