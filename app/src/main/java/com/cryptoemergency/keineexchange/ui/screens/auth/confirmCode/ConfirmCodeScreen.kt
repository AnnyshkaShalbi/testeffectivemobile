package com.cryptoemergency.keineexchange.ui.screens.auth.confirmCode

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.common.buttons.Button
import com.cryptoemergency.keineexchange.ui.common.inputs.VerificationCodeInput
import com.cryptoemergency.keineexchange.ui.common.screens.BaseScreen
import com.cryptoemergency.keineexchange.ui.screens.auth.AuthViewModel

@Composable
fun ConfirmCodeScreen(viewModel: AuthViewModel) {
    val context = LocalContext.current
    val navController = LocalNavController.current
    val formattedString = String.format(context.getString(R.string.send_on_email), viewModel.email.value)
    val code = remember { mutableStateOf(List(4) { "" }) }

    BaseScreen(
        modifier = Modifier.padding(top = 130.dp)
    ) {
        Text(
            text = formattedString,
            style = Theme.typography.title2.copy(
                color = Theme.colors.primary
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = stringResource(R.string.warning_text),
            style = Theme.typography.title3,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        VerificationCodeInput(
            code = code.value,
            onCodeChange = { newCode ->
                code.value = newCode
                viewModel.code.value = newCode.joinToString("")
            },
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                if (viewModel.code.value.length == 4) {
                    navController.navigate(Routes.Menu.Search.route)
                }
            },
            text = stringResource(R.string.confirm),
            isEnabled = viewModel.code.value.length == 4
        )
    }
}
