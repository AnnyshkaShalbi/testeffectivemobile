package com.cryptoemergency.keineexchange.ui.common.inputs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
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
fun Input(
    modifier: Modifier = Modifier,
    placeholder: String,
    textState: MutableState<String>,
    onClick: () -> Unit = {},
    errorMessage: MutableState<String?> = mutableStateOf(null),
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
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value) onClick()

    Column(modifier) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                isError.value = false
                errorMessage.value = null
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Theme.colors.grayRegular,
                    style = Theme.typography.text1,
                )
            },
            interactionSource = interactionSource,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            enabled = isEnabled,
            isError = isError.value,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Theme.colors.grayRegular,
                focusedLabelColor = Color.Transparent,
                focusedContainerColor = Theme.colors.surfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = Theme.colors.primary,

                unfocusedTextColor = Theme.colors.primary,
                unfocusedLabelColor = Color.Transparent,
                unfocusedContainerColor = Theme.colors.surfaceVariant,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = Theme.colors.surfaceVariant,

                disabledTextColor = Theme.colors.primary,
                disabledIndicatorColor = Color.Transparent,
                disabledPlaceholderColor = Theme.colors.primary,
                disabledContainerColor = Theme.colors.surfaceVariant,

                errorContainerColor = Theme.colors.surfaceVariant,
                errorPlaceholderColor = Theme.colors.primary,
            ),
            textStyle = Theme.typography.text1.copy(
                color = Theme.colors.primary
            ),

        )

        ErrorBlock(errorMessage.value)
        SuccessBlock(successMessage.value)
    }
}

@Composable
private fun ErrorBlock(errorMessage: String?) {
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = Theme.colors.error,
            style = Theme.typography.text1,
            maxLines = 1,
        )
    }
}

@Composable
private fun SuccessBlock(successMessage: String?) {
    if (successMessage != null) {
        Text(
            text = successMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = Theme.colors.primary,
            style = Theme.typography.title3,
            maxLines = 1,
        )
    }
}
