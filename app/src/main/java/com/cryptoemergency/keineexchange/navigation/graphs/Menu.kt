package com.cryptoemergency.keineexchange.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.ui.screens.menu.favorites.FavoritesScreen
import com.cryptoemergency.keineexchange.ui.screens.menu.messages.MessagesScreen
import com.cryptoemergency.keineexchange.ui.screens.menu.profile.ProfileScreen
import com.cryptoemergency.keineexchange.ui.screens.menu.responses.ResponsesScreen
import com.cryptoemergency.keineexchange.ui.screens.menu.search.SearchScreen
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel

fun NavGraphBuilder.menuNavGraph(vacanciesViewModel: VacanciesViewModel) {
    composable(Routes.Menu.Search.route) { SearchScreen(vacanciesViewModel = vacanciesViewModel) }
    composable(Routes.Menu.Favorites.route) { FavoritesScreen() }
    composable(Routes.Menu.Messages.route) { MessagesScreen() }
    composable(Routes.Menu.Responses.route) { ResponsesScreen() }
    composable(Routes.Menu.Profile.route) { ProfileScreen() }
}
