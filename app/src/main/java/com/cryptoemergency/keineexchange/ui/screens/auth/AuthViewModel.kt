package com.cryptoemergency.keineexchange.ui.screens.auth

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    var email = mutableStateOf("")
    val emailErrorMessage = mutableStateOf<String?>(null)
    val isEmailError = mutableStateOf(false)
    var code = mutableStateOf("")

    fun onValueChangeEmail(newValue: String) {
        email.value = newValue
    }
}