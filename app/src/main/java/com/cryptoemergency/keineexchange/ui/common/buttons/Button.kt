package com.cryptoemergency.keineexchange.ui.common.buttons

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.providers.theme.Theme

/**
 *
 * @param useWrapContentWidth Уточнение расположения компонента в родительском компоненте. По умолчанию fillMaxWidth()
 *
 * */
@Composable
fun Button(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    type: Type = Type.Primary,
    isEnabled: Boolean = true,
    useWrapContentWidth: Boolean = false,
) {
    val theme = when (type) {
        Type.Primary -> {
            ButtonDefaults.buttonColors(
                containerColor = Theme.colors.accentLight,
                contentColor = Theme.colors.primary,

                disabledContainerColor = Theme.colors.accentDark,
                disabledContentColor = Theme.colors.grayRegular,
            )
        }
        Type.Secondary -> {
            ButtonDefaults.buttonColors(
                containerColor = Theme.colors.greenLight,
                contentColor = Theme.colors.primary,

                disabledContainerColor = Theme.colors.greenLight,
                disabledContentColor = Theme.colors.primary,
            )
        }
        Type.Gray -> {
            ButtonDefaults.buttonColors(
                containerColor = Theme.colors.surfaceVariant,
                contentColor = Theme.colors.primary,

                disabledContainerColor = Theme.colors.surfaceVariant,
                disabledContentColor = Theme.colors.primary,
            )
        }
        Type.GreenMinRadius -> {
            ButtonDefaults.buttonColors(
                containerColor = Theme.colors.greenLight,
                contentColor = Theme.colors.primary,

                disabledContainerColor = Theme.colors.greenLight,
                disabledContentColor = Theme.colors.primary,
            )
        }
    }

    val shape = when (type) {
        Type.Primary -> RoundedCornerShape(8.dp)
        Type.Secondary -> RoundedCornerShape(50.dp)
        Type.Gray -> RoundedCornerShape(50.dp)
        Type.GreenMinRadius -> RoundedCornerShape(8.dp)
    }

    Button(
        onClick = onClick,
        colors = theme,
        modifier = modifier
            .then(
                if(type == Type.Primary || type == Type.GreenMinRadius){
                    Modifier
                        .defaultMinSize(minHeight = 40.dp)
                } else {
                    Modifier
                        .defaultMinSize(minHeight = 32.dp)
                }
            )
            .then(
                if (useWrapContentWidth) {
                    Modifier.wrapContentWidth()
                } else {
                    Modifier.fillMaxWidth()
                }
            ),
        enabled = isEnabled,
        shape = shape
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = if(type == Type.Gray) Theme.typography.title4 else Theme.typography.buttonText2,
        )
    }
}

enum class Type {
    Primary, Secondary, Gray, GreenMinRadius
}
