package com.cryptoemergency.keineexchange.navigation

/**
 *
 * Обязательно использовать префиксы для маршрутов, во избежания конфиликтов.
 *
 * Если указать что Search("search")
 *
 * Routes.Menu.Search !== "pages/about-us"
 *
 * Routes.Menu.Search == "about-us"
 *
 * */
object Routes {
    val splash = "splash"

    enum class Vacancy(
        val route: String,
        val title: String
    ) {
        Vacancies("vacancies/list-vacancies", "вакансии"),
        VacancyItem("vacancies/vacancy", "вакансия"),
    }

    enum class Auth(
        val route: String,
        val title: String,
    ) {
        Login("auth/login", "Логин"),
        ConfirmCode("auth/confirm-code", "Подтверждение кода"),
    }

    enum class Menu(
        val route: String,
        val title: String,
    ) {
        Search("menu/search", "История"),
        Favorites("menu/favorites", "Избранное"),
        Messages("menu/messages", "Сообщения"),
        Responses("menu/responses", "Отклики"),
        Profile("menu/profile", "Профиль"),
    }
}
