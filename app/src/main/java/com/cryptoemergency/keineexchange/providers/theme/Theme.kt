package com.cryptoemergency.keineexchange.providers.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.repository.store.data.CurrentTheme

var currentTheme by mutableStateOf(CurrentTheme.NULL)

@Composable
fun MainTheme(
    content: @Composable () -> Unit,
) {
    val colors = remember { mutableStateOf(darkPalette) }

    LaunchedEffect(Unit) {
        // Установить тему согласно теме из системы
        if (currentTheme == CurrentTheme.NULL) {
            // TODO: исправить в зависимости от системной темы
            currentTheme = CurrentTheme.DARK
        }
    }

    LaunchedEffect(currentTheme) {
        colors.value = when (currentTheme) {
            CurrentTheme.DARK -> darkPalette
            CurrentTheme.LIGHT -> lightPalette
            CurrentTheme.NULL -> darkPalette
        }
    }

    val shapes = Shape(
        padding = 24.dp
    )

    CompositionLocalProvider(
        LocalColors provides colors.value,
        LocalTypography provides typography,
        LocalShape provides shapes,
        content = content,
    )
}
