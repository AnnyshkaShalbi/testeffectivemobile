package com.cryptoemergency.keineexchange.ui.screens.vacancies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.lib.formatPublishedDate
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.repository.store.data.Vacancy
import com.cryptoemergency.keineexchange.ui.common.ConditionModalBottom
import com.cryptoemergency.keineexchange.ui.common.CustomCard
import com.cryptoemergency.keineexchange.ui.common.buttons.Button
import com.cryptoemergency.keineexchange.ui.common.buttons.Type
import com.cryptoemergency.keineexchange.ui.common.screens.BaseScreen

@Composable
fun VacanciesItem(
    modifier: Modifier = Modifier,
    vacancy: Vacancy,
    viewModel: VacanciesViewModel,
    onClick: () -> Unit
) {
    var openModalVacancy = remember { mutableStateOf(false) }

    CustomCard(
        modifier = modifier.clickable { onClick() },
        innerPaddingCard = Modifier.padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Основное содержимое карточки
            Column {
                if (vacancy.lookingNumber != null && vacancy.lookingNumber > 0) {
                    Text(
                        text = "Сейчас просматривает ${vacancy.lookingNumber} человек",
                        style = Theme.typography.text1.copy(
                            color = Theme.colors.greenLight
                        ),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                Text(
                    text = vacancy.title,
                    style = Theme.typography.title3.copy(
                        color = Theme.colors.primary
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}",
                    style = Theme.typography.text1.copy(
                        color = Theme.colors.primary
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(
                        text = vacancy.company,
                        style = Theme.typography.text1.copy(
                            color = Theme.colors.primary
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.done),
                        contentDescription = "null",
                        modifier = Modifier.size(16.dp),
                        tint = Theme.colors.grayDark
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.briefcase),
                        contentDescription = "null",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp),
                        tint = Theme.colors.primary
                    )
                    Text(
                        text = vacancy.experience.previewText,
                        style = Theme.typography.text1.copy(
                            color = Theme.colors.primary
                        )
                    )
                }
                Text(
                    text = formatPublishedDate(vacancy.publishedDate),
                    style = Theme.typography.text1.copy(
                        color = Theme.colors.grayDark
                    ),
                    modifier = Modifier.padding(bottom = 21.dp)
                )
                Button(
                    onClick = { openModalVacancy.value = true },
                    modifier = Modifier
                        .fillMaxWidth(),
                    type = Type.Secondary,
                    text = "Откликнуться",
                )
            }

            // Иконка в верхнем правом углу
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                val icon = if (vacancy.isFavorite) R.drawable.heart_full else R.drawable.heart
                val colorTint = if (vacancy.isFavorite) Theme.colors.accentLight else Theme.colors.primary
                Icon(
                    painter = painterResource(icon),
                    contentDescription = if (vacancy.isFavorite) "Remove from favorites" else "Add to favorites",
                    modifier = Modifier.size(24.dp),
                    tint = colorTint
                )
            }
        }
    }

    ConditionModalBottom(
        viewModel = viewModel,
        onDismiss = {
            viewModel.resetSelectedQuestion()
        }
    ) {
        BaseScreen {
            Row {
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "Аватар пользователя",
                    modifier = Modifier.size(48.dp)
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Резюме для отклика",
                        style = Theme.typography.text1.copy(
                            color = Theme.colors.grayDark
                        )
                    )
                    viewModel.selectedQuestion.value?.let {
                        Text(
                            text = it,
                            style = Theme.typography.title3
                        )
                    }
                }
            }
            Divider(color = Theme.colors.surface)
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Добавить сопроводительное письмо",
                    style = Theme.typography.title3.copy(
                        color = Theme.colors.greenLight
                    )
                )
            }
            Button(
                onClick = { },
                type = Type.Secondary,
                text = "Откликнуться",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}