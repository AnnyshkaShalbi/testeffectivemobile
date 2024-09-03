package com.cryptoemergency.keineexchange.ui.components.topBar

import androidx.compose.runtime.Composable
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.getCurrentRoute
import com.cryptoemergency.keineexchange.ui.components.topBar.components.ScreenTopBar
import com.cryptoemergency.keineexchange.ui.components.topBar.components.VacanciesTopBar
import com.cryptoemergency.keineexchange.ui.components.topBar.components.VacancyTopBar
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel

@Composable
fun TopBar(vacanciesViewModel: VacanciesViewModel) {
    val currentRoute = getCurrentRoute()

    when (currentRoute) {
        // menu pages from bottom nav
        Routes.Menu.Search.route -> { EmptyTopBar() }
        Routes.Menu.Favorites.route -> { EmptyTopBar() }
        Routes.Menu.Messages.route -> { EmptyTopBar() }
        Routes.Menu.Responses.route -> { EmptyTopBar() }
        Routes.Menu.Responses.route -> { EmptyTopBar() }

        Routes.Auth.Login.route -> { EmptyTopBar() }
        Routes.Auth.ConfirmCode.route -> { EmptyTopBar() }

        Routes.Vacancy.VacancyItem.route -> { VacancyTopBar(vacanciesViewModel = vacanciesViewModel) }
        Routes.Vacancy.Vacancies.route -> { VacanciesTopBar() }

        Routes.splash -> { EmptyTopBar() }

        else -> ScreenTopBar()
    }
}

@Composable
private fun EmptyTopBar() { }
