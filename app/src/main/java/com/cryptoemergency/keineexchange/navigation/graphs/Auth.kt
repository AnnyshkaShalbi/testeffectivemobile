package com.cryptoemergency.keineexchange.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.ui.screens.auth.AuthViewModel
import com.cryptoemergency.keineexchange.ui.screens.auth.confirmCode.ConfirmCodeScreen
import com.cryptoemergency.keineexchange.ui.screens.auth.login.LoginScreen

fun NavGraphBuilder.authNavGraph(authViewModel: AuthViewModel) {
    composable(Routes.Auth.Login.route) { LoginScreen(authViewModel) }
    composable(Routes.Auth.ConfirmCode.route) { ConfirmCodeScreen(authViewModel) }
}