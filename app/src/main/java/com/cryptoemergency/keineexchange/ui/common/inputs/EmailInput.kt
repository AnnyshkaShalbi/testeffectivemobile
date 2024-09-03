package com.cryptoemergency.keineexchange.ui.common.inputs

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.lib.validateEmail
import com.cryptoemergency.keineexchange.providers.theme.Theme

/**
 *
 * @param leadingIcon необязательный начальный значок, который будет отображаться в начале контейнера текстового поля
 *
 * @param trailingIcon необязательный завершающий значок, который будет отображаться в конце контейнера текстового поля
 *
 * @param textState текущее состояние текста
 *
 */
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    textState: MutableState<String>,
    errorMessage: MutableState<String?> = mutableStateOf(null),
    successMessage: MutableState<String?> = mutableStateOf(null),
    onValidate: (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isEnabled: Boolean = true,
    isError: MutableState<Boolean> = mutableStateOf(false),
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    ValidateInput(
        modifier = modifier,
        isError = isError,
        placeholder = stringResource(R.string.placeholder_email),
        textState = textState,
        keyboardActions = keyboardActions,
        errorMessage = errorMessage,
        successMessage = successMessage,
        leadingIcon = leadingIcon,
        isEnabled = isEnabled,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Email,
        ),
        onValidate = {
            if (onValidate == null) {
                validateEmail(
                    textState.value,
                    errorMessage,
                    isError
                )
            } else {
                onValidate()
            }
        },
        trailingIcon = {
            if (textState.value.isNotEmpty()) {
                IconButton(onClick = {
                    textState.value = ""
                    isError.value = false
                    errorMessage.value = null
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Clear text",
                        tint = Theme.colors.grayRegular
                    )
                }
            }
        }
    )
}