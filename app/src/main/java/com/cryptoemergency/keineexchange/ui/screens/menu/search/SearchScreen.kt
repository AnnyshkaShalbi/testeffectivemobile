package com.cryptoemergency.keineexchange.ui.screens.menu.search

import Spinner
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.api.network.ApiResponse
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.repository.requests.common.ErrorResponse
import com.cryptoemergency.keineexchange.repository.store.data.OffersResponse
import com.cryptoemergency.keineexchange.ui.common.CustomSlider
import com.cryptoemergency.keineexchange.ui.common.buttons.Button
import com.cryptoemergency.keineexchange.ui.common.inputs.ValidateInput
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesItem
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    vacanciesViewModel: VacanciesViewModel
) {
    val offers by viewModel.offers.collectAsState()

    when (offers) {
        is ApiResponse.Success -> {
            val data = (offers as ApiResponse.Success<OffersResponse>).data
            val navController = LocalNavController.current

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { SearchInput(viewModel = viewModel) }
                item { CustomSlider(list = data.offers) }
                item { TitleSearchScreen() }
                items(data.vacancies.take(3).size) { index ->
                    VacanciesItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        vacancy = data.vacancies[index],
                        viewModel = vacanciesViewModel
                    ) {
                        vacanciesViewModel.currentIdVacancy.value = data.vacancies[index].id
                        vacanciesViewModel.fillItemVacancy(vacanciesViewModel.currentIdVacancy.value!!)
                        navController.navigate(Routes.Vacancy.VacancyItem.route)
                    }
                }
                item {
                    Button(
                        onClick = {
                            navController.navigate(Routes.Vacancy.Vacancies.route)
                        },
                        text = "Ещё ${data.vacancies.size} вакансий",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }

        is ApiResponse.Error -> {
            val error = (offers as ApiResponse.Error<ErrorResponse>).data
            Log.d("error", "$error")
            Spinner()
        }

        null -> {
            Log.d("error", "null")
            Spinner()
        }
    }
}

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel
) {
    val errorMessage = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(viewModel.search.value) {
        viewModel.onChangeSearchValue(viewModel.search.value)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ValidateInput(
            modifier = Modifier.weight(1f),
            placeholder = "Поиск по вакансиям",
            textState = viewModel.search,
            onValidate = { /*TODO*/ },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = null,
                    tint = Theme.colors.grayRegular
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            errorMessage = errorMessage,
            trailingIcon = {
                if (viewModel.search.value.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.search.value = ""
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
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { },
            modifier = Modifier
                .background(Theme.colors.surfaceVariant, RoundedCornerShape(8.dp))
                .size(50.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "Фильтр поиска",
                tint = Theme.colors.primary
            )
        }
    }
}

@Composable
fun TitleSearchScreen(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.vacancies_for_you),
        style = Theme.typography.title2.copy(
            color = Theme.colors.primary
        ),
        modifier = modifier.padding(horizontal = 16.dp)
    )
}
