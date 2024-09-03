package com.cryptoemergency.keineexchange.ui.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.repository.store.data.Offer

/**
 *
 * @param List<Offer>
 *
 * Список предложений - при клике открывается браузер по ссылке
 *
 * */

@Composable
fun CustomSlider(
    list: List<Offer>,
) {
    val context = LocalContext.current

    if (list.isNotEmpty()) {
        LazyRow(
            modifier = Modifier.padding(bottom = 10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(list) { index, item ->
                CategoryItem(
                    item = item,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                        context.startActivity(intent)
                    },
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    item: Offer,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val icon = when (item.id) {
        "near_vacancies" -> {
            R.drawable.eye
        }

        "level_up_resume" -> {
            R.drawable.star
        }

        "temporary_job" -> {
            R.drawable.list_check
        }

        else -> {
            0
        }
    }

    val iconBackground =
        if (item.id == "near_vacancies") Theme.colors.accentDark else Theme.colors.greenDark

    CustomCard(
        modifier = modifier
            .width(132.dp)
            .height(120.dp)
            .clickable { onClick() },
        innerPaddingCard = Modifier.padding(vertical = 10.dp, horizontal = 8.dp)
    ) {
        if (icon != 0) {
            CircularIconComponent(
                modifier = Modifier.padding(bottom = 16.dp),
                backgroundColor = iconBackground,
                icon = icon,
            )
            Text(
                text = item.title.trim().take(60),
                style = Theme.typography.title4,
                maxLines = if (item.button?.text?.isNotEmpty() == true) 2 else 3,
                overflow = TextOverflow.Ellipsis
            )
            if (item.button != null) {
                Text(
                    text = item.button.text,
                    style = Theme.typography.text1.copy(
                        color = Theme.colors.greenLight
                    )
                )
            }
        }
        if (icon == 0) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = item.title.trim().take(60),
                    style = Theme.typography.title4,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    maxLines = if (item.button?.text?.isNotEmpty() == true) 2 else 3,
                    overflow = TextOverflow.Ellipsis
                )
                if (item.button?.text?.isNotEmpty() == true) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = item.button.text,
                            style = Theme.typography.text1.copy(
                                color = Theme.colors.greenLight
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CircularIconComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: Int,
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (icon != 0) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null, // Provide a content description if necessary
                modifier = Modifier.size(20.dp),
                tint = Theme.colors.primary
            )
        }

    }
}
