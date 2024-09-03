package com.cryptoemergency.keineexchange.ui.components.bottomBar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.getCurrentRoute
import com.cryptoemergency.keineexchange.providers.localNavController.getNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme

@Composable
fun CommonBar() {
    data class NavItem(val route: String, val icon: Int, val title: String)

    val navItems = listOf(
        NavItem(Routes.Menu.Search.route, R.drawable.search, "Поиск"),
        NavItem(Routes.Menu.Favorites.route, R.drawable.heart, "Избранное"),
        NavItem(Routes.Menu.Responses.route, R.drawable.letter, "Отклики"),
        NavItem(Routes.Menu.Messages.route, R.drawable.message, "Сообщения"),
        NavItem(Routes.Menu.Profile.route, R.drawable.profile, "Профиль")
    )
    val navController = getNavController()

    Column {
        Divider(color = Theme.colors.surface)

        NavigationBar(
            containerColor = Theme.colors.background,
        ) {
            val currentRoute = getCurrentRoute()

            navItems.forEach {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.title,
                        )
                    },
                    label = {
                        Text(
                            text = it.title,
                            style = Theme.typography.tabText,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    },
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Theme.colors.accentLight,
                        unselectedIconColor = Theme.colors.grayRegular,
                        selectedTextColor = Theme.colors.accentLight,
                        unselectedTextColor = Theme.colors.grayRegular,
                        indicatorColor = Theme.colors.background,
                    ),
                )
            }
        }
    }
}
