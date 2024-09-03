package com.cryptoemergency.keineexchange.lib

import kotlinx.datetime.LocalDate

fun formatPublishedDate(dateString: String): String {
    val date = LocalDate.parse(dateString)
    val monthNames = arrayOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )
    val day = date.dayOfMonth
    val month = monthNames[date.monthNumber - 1]
    return "Опубликовано $day $month"
}