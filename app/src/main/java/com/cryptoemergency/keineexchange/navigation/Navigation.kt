package com.cryptoemergency.keineexchange.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cryptoemergency.keineexchange.navigation.graphs.authNavGraph
import com.cryptoemergency.keineexchange.navigation.graphs.menuNavGraph
import com.cryptoemergency.keineexchange.navigation.graphs.vacancyNavGraph
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.ui.screens.auth.AuthViewModel
import com.cryptoemergency.keineexchange.ui.screens.splash.SplashScreen
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel

@Composable
fun Navigation(vacanciesViewModel: VacanciesViewModel) {
    val navController = LocalNavController.current
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(navController, startDestination = Routes.splash) {
        composable(Routes.splash) { SplashScreen() }

        menuNavGraph(vacanciesViewModel)
        authNavGraph(authViewModel)
        vacancyNavGraph(vacanciesViewModel)
    }
}
