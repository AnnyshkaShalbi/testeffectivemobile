package com.cryptoemergency.keineexchange.lib

import androidx.compose.runtime.MutableState

fun validateEmail(
    email: String,
    errorMessage: MutableState<String?>,
    isError: MutableState<Boolean>,
) {
    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    isError.value = !email.matches(emailRegex)
    if (isError.value) {
        errorMessage.value = "Вы ввели неверный e-mail"
    } else {
        errorMessage.value = null
    }
}

