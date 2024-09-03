package com.cryptoemergency.keineexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.keineexchange.navigation.Navigation
import com.cryptoemergency.keineexchange.providers.localNavController.NavController
import com.cryptoemergency.keineexchange.providers.localSnackBar.LocalSnackbar
import com.cryptoemergency.keineexchange.providers.localSnackBar.SnackBar
import com.cryptoemergency.keineexchange.providers.theme.MainTheme
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.components.bottomBar.BottomBar
import com.cryptoemergency.keineexchange.ui.components.topBar.TopBar
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainTheme {
                NavController {
                    SnackBar {
                        MainScreen()
                    }
                }

                val colors = Theme.colors
                LaunchedEffect(Unit) {
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    window.statusBarColor = colors.background.toArgb()
                    window.navigationBarColor = colors.background.toArgb()
                    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val snackbar = LocalSnackbar.current
    val vacanciesViewModel: VacanciesViewModel = hiltViewModel()

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        contentColor = Theme.colors.primary,
        containerColor = Theme.colors.background,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbar,
                modifier = Modifier.imePadding(),
            )
        },
        bottomBar = { BottomBar() },
        topBar = { TopBar(vacanciesViewModel = vacanciesViewModel) },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Navigation(vacanciesViewModel = vacanciesViewModel)
        }
    }
}
