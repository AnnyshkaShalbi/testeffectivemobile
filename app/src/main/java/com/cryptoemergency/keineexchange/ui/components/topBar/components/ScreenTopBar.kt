package com.cryptoemergency.keineexchange.ui.components.topBar.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    title: String = "",
    @DrawableRes icon: Int = R.drawable.heart,
    onClick: (() -> Unit)? = null,
) {
    val navController = LocalNavController.current

    CenterAlignedTopAppBar(
        modifier = Modifier.padding(horizontal = 10.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Theme.colors.primary,
            navigationIconContentColor = Theme.colors.primary,
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Theme.typography.title1,
                modifier = Modifier.padding(horizontal = Theme.shaped.padding)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if(onClick != null) onClick()
                else navController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "Вернуться назад",
                )
            }
        },
    )
}
