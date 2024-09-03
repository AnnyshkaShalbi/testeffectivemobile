package com.cryptoemergency.keineexchange.lib

fun getPeopleCountText(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "человек"
        count % 10 in 2..4 && (count % 100 < 10 || count % 100 >= 20) -> "человека"
        else -> "человек"
    }
}