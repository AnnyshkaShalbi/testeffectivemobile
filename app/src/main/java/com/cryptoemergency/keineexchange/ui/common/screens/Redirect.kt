package com.cryptoemergency.keineexchange.ui.common.screens

data class Redirect(
    var route: String,
    var popBackStack: Boolean = false,
)
