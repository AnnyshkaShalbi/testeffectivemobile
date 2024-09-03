package com.cryptoemergency.keineexchange.ui.components.topBar.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyTopBar(
    vacanciesViewModel: VacanciesViewModel,
    @DrawableRes icon: Int = R.drawable.arrow_left,
    onClick: (() -> Unit)? = null,
) {
    val navController = LocalNavController.current

    TopAppBar(
        modifier = Modifier.padding(horizontal = 10.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Theme.colors.primary,
            navigationIconContentColor = Theme.colors.primary,
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                if(onClick != null) onClick()
                else navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "Вернуться назад",
                    tint = Theme.colors.primary
                )
            }
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.eye),
                    contentDescription = "null",
                    tint = Theme.colors.primary)
                Icon(
                    painter = painterResource(R.drawable.share),
                    contentDescription = "null",
                    tint = Theme.colors.primary)
                Icon(
                    painter = if(vacanciesViewModel.currentItemVacancy.value?.isFavorite == true)
                        painterResource(R.drawable.heart_full) else
                            painterResource(R.drawable.heart),
                    contentDescription = "null",
                    tint = if(vacanciesViewModel.currentItemVacancy.value?.isFavorite == true)
                        Theme.colors.accentLight else Theme.colors.primary)
            }
        }
    )
}