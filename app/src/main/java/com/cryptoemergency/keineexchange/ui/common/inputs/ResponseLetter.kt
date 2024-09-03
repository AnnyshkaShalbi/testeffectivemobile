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
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.providers.theme.Theme

@Composable
fun ResponseLetter(
    modifier: Modifier = Modifier,
    placeholder: String,
    textState: MutableState<String>,
    onClick: () -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isEnabled: Boolean = true,
    isError: MutableState<Boolean> = mutableStateOf(false),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value) onClick()

    val annotatedPlaceholder = if (textState.value.isEmpty()) {
        AnnotatedString(placeholder)
    } else {
        AnnotatedString(placeholder)
    }

    Column {
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            placeholder = {
                Text(
                    text = annotatedPlaceholder,
                    color = Theme.colors.primary,
                    style = Theme.typography.text1,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            },
            interactionSource = interactionSource,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            maxLines = 5,
            enabled = isEnabled,
            isError = isError.value,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Theme.colors.grayRegular,
                focusedLabelColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = Theme.colors.primary,

                unfocusedTextColor = Theme.colors.primary,
                unfocusedLabelColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Transparent,

                disabledTextColor = Theme.colors.primary,
                disabledIndicatorColor = Color.Transparent,
                disabledPlaceholderColor = Theme.colors.primary,
                disabledContainerColor = Color.Transparent,

                errorContainerColor = Color.Transparent,
                errorPlaceholderColor = Theme.colors.primary,
            ),
            textStyle = Theme.typography.text1.copy(
                color = Theme.colors.primary
            )
        )
        Divider(color = Theme.colors.surface)
    }
}