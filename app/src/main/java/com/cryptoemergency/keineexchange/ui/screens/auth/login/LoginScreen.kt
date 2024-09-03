package com.cryptoemergency.keineexchange.ui.screens.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.lib.validateEmail
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.common.CustomCard
import com.cryptoemergency.keineexchange.ui.common.buttons.Button
import com.cryptoemergency.keineexchange.ui.common.buttons.Type
import com.cryptoemergency.keineexchange.ui.common.inputs.EmailInput
import com.cryptoemergency.keineexchange.ui.common.screens.BaseScreen
import com.cryptoemergency.keineexchange.ui.screens.auth.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel = hiltViewModel()) {
    BaseScreen {
        Text(
            text = "Вход в личный кабинет",
            style = Theme.typography.title2.copy(
                color = Theme.colors.primary
            ),)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            LoginIndividual(viewModel = viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            LoginForEmployer()
        }
    }
}

@Composable
fun LoginIndividual(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel
) {
    CustomCard(modifier = modifier) {
        Text(
            text = "Поиск работы",
            style = Theme.typography.title3.copy(
                color = Theme.colors.primary
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LoginEmailInput(
            modifier = Modifier.padding(bottom = 16.dp),
            viewModel = viewModel
        )
        LoginButtons(viewModel = viewModel)
    }
}

@Composable
fun LoginEmailInput(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel
) {
    LaunchedEffect(viewModel.email.value) {
        viewModel.onValueChangeEmail(viewModel.email.value)
    }
    EmailInput(
        modifier = modifier,
        textState = viewModel.email,
        errorMessage = viewModel.emailErrorMessage,
        isError = viewModel.isEmailError,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.letter),
                contentDescription = null,
                tint = Theme.colors.grayRegular
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        onValidate = {
            validateEmail(
                viewModel.email.value,
                viewModel.emailErrorMessage,
                viewModel.isEmailError
            )
        }
    )
}

@Composable
fun LoginButtons(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel
) {
    val navController = LocalNavController.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        Button(
            onClick = {
                validateEmail(viewModel.email.value, viewModel.emailErrorMessage, viewModel.isEmailError)
                if (!viewModel.isEmailError.value) {
                    navController.navigate(Routes.Auth.ConfirmCode.route)
                }
                      },
            modifier = Modifier.width(167.dp),
            text = stringResource(R.string.continue_next),
            isEnabled = viewModel.email.value.isNotEmpty()
        )
        TextButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.input_with_pass),
                style = Theme.typography.buttonText2.copy(
                    color = Theme.colors.accentLight
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoginForEmployer(modifier: Modifier = Modifier) {
    CustomCard(modifier) {
        Text(
            text = "Поиск сотрудников",
            style = Theme.typography.title3.copy(
                color = Theme.colors.primary
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Размещение вакансий и доступ к базе резюме",
            style = Theme.typography.text1.copy(
                color = Theme.colors.primary
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(),
            type = Type.Secondary,
            text = "Я ищу сотрудников",
        )
    }
}