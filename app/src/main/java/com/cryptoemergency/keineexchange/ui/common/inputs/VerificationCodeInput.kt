package com.cryptoemergency.keineexchange.ui.common.inputs

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.providers.theme.Theme

@Composable
fun VerificationCodeInput(
    modifier: Modifier = Modifier,
    code: List<String>,
    onCodeChange: (List<String>) -> Unit
) {
    val focusRequesters = remember { List(4) { FocusRequester() } }
    var focusedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(focusedIndex) {
        focusRequesters[focusedIndex].requestFocus()
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(4) { index ->
            CodeInputCell(
                value = code[index],
                onValueChange = { newValue ->
                    val newCode = code.toMutableList()
                    newCode[index] = newValue
                    onCodeChange(newCode)
                    if (newValue.isNotEmpty() && index < 3) {
                        focusedIndex = index + 1
                    } else if (newValue.isEmpty() && index > 0) {
                        focusedIndex = index - 1
                    }
                },
                onFocusChange = { isFocused ->
                    if (isFocused) {
                        focusedIndex = index
                    }
                },
                focusRequester = focusRequesters[index]
            )
        }
    }
}

@Composable
fun CodeInputCell(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    focusRequester: FocusRequester
) {
    val interactionSource = remember { MutableInteractionSource() }
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                onValueChange(it)
            }
        },
        modifier = Modifier
            .size(48.dp)
            .focusable()
            .onFocusChanged { onFocusChange(it.isFocused) }
            .focusRequester(focusRequester),
        placeholder = {
            Text(
                text = "",
                color = Theme.colors.grayRegular,
                style = Theme.typography.text1,
            )
        },
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onAny = {}
        ),
        singleLine = true,
        maxLines = 1,
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Theme.colors.primary,
            focusedIndicatorColor = Theme.colors.surfaceVariant,
            focusedContainerColor = Theme.colors.surfaceVariant,
            unfocusedTextColor = Theme.colors.primary,
            unfocusedIndicatorColor = Theme.colors.surfaceVariant,
            unfocusedContainerColor = Theme.colors.surfaceVariant,
            disabledTextColor = Theme.colors.primary,
            disabledIndicatorColor = Theme.colors.surfaceVariant,
            disabledContainerColor = Theme.colors.surfaceVariant,
        ),
        textStyle = Theme.typography.text1.copy(
            color = Theme.colors.primary,
            textAlign = TextAlign.Center
        ),
    )
}
