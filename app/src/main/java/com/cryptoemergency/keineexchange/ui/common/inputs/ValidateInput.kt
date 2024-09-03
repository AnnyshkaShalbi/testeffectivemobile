package com.cryptoemergency.keineexchange.ui.common.inputs

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.VisualTransformation

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
fun ValidateInput(
    modifier: Modifier = Modifier,
    placeholder: String,
    textState: MutableState<String>,
    onValidate: () -> Unit,
    onClick: () -> Unit = {},
    errorMessage: MutableState<String?>,
    successMessage: MutableState<String?> = mutableStateOf(null),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isEnabled: Boolean = true,
    isError: MutableState<Boolean> = mutableStateOf(false),
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    Input(
        modifier = modifier.onFocusChanged { focusState ->
            if (!focusState.isFocused && textState.value.isNotEmpty()) {
                onValidate()
            }
        },
        onClick = onClick,
        isError = isError,
        placeholder = placeholder,
        textState = textState,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        errorMessage = errorMessage,
        successMessage = successMessage,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isEnabled = isEnabled,
        singleLine = singleLine,
    )
}
