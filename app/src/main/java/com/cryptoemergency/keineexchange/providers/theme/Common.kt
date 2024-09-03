package com.cryptoemergency.keineexchange.providers.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val accentLight: Color,
    val accentDark: Color,
    val greenLight: Color,
    val greenDark: Color,
    val error: Color,

    val background: Color,
    val backgroundVariant: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val grayDark: Color,
    val grayRegular: Color,
    val grayLight: Color,
    val primary: Color,

)

data class Typography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val title4: TextStyle,
    val text1: TextStyle,
    val buttonText1: TextStyle,
    val buttonText2: TextStyle,
    val tabText: TextStyle,
    val number: TextStyle,
    val fontText: TextStyle,
)

data class Shape(
    val padding: Dp,
)

object Theme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shaped: Shape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}

val LocalColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalTypography = staticCompositionLocalOf<Typography> {
    error("No typography provided")
}

val LocalShape = staticCompositionLocalOf<Shape> {
    error("No typography provided")
}
