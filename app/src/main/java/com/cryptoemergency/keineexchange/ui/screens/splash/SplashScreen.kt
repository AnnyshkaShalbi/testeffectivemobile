package com.cryptoemergency.keineexchange.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.common.screens.Redirect

@Composable
fun SplashScreen() {
    val navController = LocalNavController.current
    val alpha = remember { Animatable(0f) }
    val redirect = remember { mutableStateOf<Redirect?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.hh_circle),
            contentDescription = null,
            modifier = Modifier
                .size(width = 265.dp, height = 92.dp)
                .alpha(alpha.value),
            contentScale = ContentScale.Fit
        )
    }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(2000))
        redirect.value = Redirect(Routes.Auth.Login.route, true)
    }

    LaunchedEffect(redirect.value?.route) {
        redirect.value?.route?.let { url ->
            if (redirect.value?.popBackStack == true) {
                navController.popBackStack()
            }

            navController.navigate(url)
        }
    }
}
