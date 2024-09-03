package com.cryptoemergency.keineexchange.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesScreen
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacancyScreen

fun NavGraphBuilder.vacancyNavGraph(vacanciesViewModel: VacanciesViewModel) {

    composable(Routes.Vacancy.Vacancies.route) { VacanciesScreen(vacanciesViewModel = vacanciesViewModel) }
    composable(Routes.Vacancy.VacancyItem.route) { VacancyScreen(vacanciesViewModel = vacanciesViewModel) }
}