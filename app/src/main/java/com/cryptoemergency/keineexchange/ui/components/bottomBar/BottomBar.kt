package com.cryptoemergency.keineexchange.ui.components.bottomBar

import androidx.compose.runtime.Composable
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.getCurrentRoute
import com.cryptoemergency.keineexchange.ui.components.bottomBar.components.CommonBar
import com.cryptoemergency.keineexchange.ui.components.bottomBar.components.EmptyBar

@Composable
fun BottomBar() {
    val currentRoute = getCurrentRoute()
    val routesWithEmptyBar = arrayOf(
        Routes.splash,
    )

    when (currentRoute) {
        in routesWithEmptyBar -> EmptyBar()

        else -> CommonBar()
    }
}
