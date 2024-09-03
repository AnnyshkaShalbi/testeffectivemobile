package com.cryptoemergency.keineexchange.ui.screens.vacancies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.lib.getPeopleCountText
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.repository.store.data.Vacancy
import com.cryptoemergency.keineexchange.ui.common.ConditionModalBottom
import com.cryptoemergency.keineexchange.ui.common.CustomCard
import com.cryptoemergency.keineexchange.ui.common.buttons.Button
import com.cryptoemergency.keineexchange.ui.common.buttons.Type
import com.cryptoemergency.keineexchange.ui.common.inputs.ResponseLetter
import java.util.Locale

@Composable
fun VacancyScreen(vacanciesViewModel: VacanciesViewModel) {
    val vacancy = vacanciesViewModel.currentItemVacancy.value
    val listQuestions = vacanciesViewModel.currentItemVacancy.value?.questions

    if (vacancy != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { VacancyBasicRequirements(vacancy = vacancy) }
            item {
                VacancyViewsApplied(
                    modifier = Modifier.padding(vertical = 24.dp),
                    vacancy = vacancy)
            }
            item { VacancyCompanyInfo(vacancy = vacancy) }
            item {
                VacancyAbout(
                    modifier = Modifier.padding(vertical = 16.dp),
                    vacancy = vacancy)
            }
            item { VacancyAskAQuestion() }
            if (listQuestions != null) {
                items(listQuestions.size) { index ->
                    val item = listQuestions[index]
                    Button(
                        onClick = {
                            vacanciesViewModel.openModalResponseQuestion(
                                question = item,
                                candidate = vacancy.title
                                )
                        },
                        type = Type.Gray,
                        text = item,
                        useWrapContentWidth = true
                    )
                }
            }
            item {
                Button(
                    onClick = { vacanciesViewModel.openModalResponse() },
                    type = Type.GreenMinRadius,
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(R.string.respond))
            }
        }
    }

    ConditionModalBottom(
        viewModel = vacanciesViewModel,
        onDismiss = {
            vacanciesViewModel.resetSelectedQuestion()
        }
    ) {
        val selectedCandidate = vacanciesViewModel.selectedCandidate.value
        var addResponseLetter = remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
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
                    if (selectedCandidate != null) {
                        Text(
                            text = selectedCandidate,
                            style = Theme.typography.title3
                        )
                    }
                }
            }
            Divider(color = Theme.colors.surface)
            TextButton(onClick = {
                addResponseLetter.value = true
            }) {
                if(!addResponseLetter.value) {
                    Text(
                        text = "Добавить сопроводительное письмо",
                        style = Theme.typography.title3.copy(
                            color = Theme.colors.greenLight
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            AnimatedVisibility(
                visible = addResponseLetter.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                VacancyResponseInput(
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                    viewModel = vacanciesViewModel
                )
            }
            Button(
                onClick = { },
                type = Type.GreenMinRadius,
                text = stringResource(R.string.respond),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun VacancyBasicRequirements(
    modifier: Modifier = Modifier,
    vacancy: Vacancy
) {
    val formattedSchedules = vacancy.schedules.joinToString(", ") { schedule ->
        if (vacancy.schedules.indexOf(schedule) == 0) {
            schedule.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        } else {
            schedule
        }
    }

    Column(modifier = modifier) {
        Text(
            text = vacancy.title,
            style = Theme.typography.title1,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        vacancy.salary.full?.let {
            Text(
                text = it,
                style = Theme.typography.text1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Text(
            text = "Требуемый опыт: ${vacancy.experience.text}",
            style = Theme.typography.text1,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = formattedSchedules, style = Theme.typography.text1,
        )
    }
}

@Composable
fun VacancyViewsApplied(
    modifier: Modifier = Modifier,
    vacancy: Vacancy
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (vacancy.appliedNumber != null) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .background(
                        color = Theme.colors.greenDark,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(text = "${vacancy.appliedNumber} ${getPeopleCountText(vacancy.appliedNumber)} уже откликнулись")
                    VacancyCircularIcon(
                        modifier = Modifier,
                        backgroundColor = Theme.colors.greenLight,
                        icon = R.drawable.profile,
                    )
                }
            }
        }
        if (vacancy.lookingNumber != null) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .background(
                        color = Theme.colors.greenDark,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(text = "${vacancy.lookingNumber} ${getPeopleCountText(vacancy.lookingNumber)} сейчас смотрят")
                    VacancyCircularIcon(
                        modifier = Modifier,
                        backgroundColor = Theme.colors.greenLight,
                        icon = R.drawable.eye,
                    )
                }
            }
        }
    }
}

@Composable
fun VacancyCompanyInfo(
    modifier: Modifier = Modifier,
    vacancy: Vacancy
) {
    CustomCard(
        modifier = modifier,
        innerPaddingCard = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(
                text = vacancy.company,
                style = Theme.typography.title3,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
                painter = painterResource(R.drawable.done),
                contentDescription = "Название компании",
                modifier = Modifier.size(16.dp),
                tint = Theme.colors.grayDark
            )
        }
        Image(
            painter = painterResource(R.drawable.maps),
            contentDescription = "Карта с адресом компании",
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        )
        Text(
            text = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}",
            style = Theme.typography.title3,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun VacancyCircularIcon(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: Int,
) {
    Box(
        modifier = modifier
            .size(16.dp)
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (icon != 0) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(8.dp),
                tint = Theme.colors.primary
            )
        }

    }
}

@Composable
fun VacancyAbout(
    modifier: Modifier = Modifier,
    vacancy: Vacancy
) {
    Text(
        text = AnnotatedString(vacancy.description),
        modifier = modifier,
        style = Theme.typography.text1,
    )
    Text(
        text = "Ваши задачи",
        style = Theme.typography.title2
    )
    Text(
        text = AnnotatedString(vacancy.responsibilities),
        modifier = modifier,
        style = Theme.typography.text1
    )
}

@Composable
fun VacancyAskAQuestion() {
    Text(
        text = "Задайте вопрос работодателю",
        style = Theme.typography.title4,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = "Он получит его с откликом на вакансию",
        style = Theme.typography.title4.copy(
            color = Theme.colors.grayDark
        ),
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun VacancyResponseInput(
    modifier: Modifier = Modifier,
    viewModel: VacanciesViewModel
) {
    LaunchedEffect(viewModel.responseText.value) {
        viewModel.onChangeResponseValue(viewModel.responseText.value)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ResponseLetter(
            placeholder = "Ваше сопроводительное письмо",
            textState = viewModel.responseText,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            trailingIcon = {
                if (viewModel.responseText.value.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.responseText.value = ""
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "Clear text",
                            tint = Theme.colors.grayRegular
                        )
                    }
                }
            }
        )
    }
}
