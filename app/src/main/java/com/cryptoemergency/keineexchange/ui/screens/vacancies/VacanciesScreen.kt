package com.cryptoemergency.keineexchange.ui.screens.vacancies

import Spinner
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.api.network.ApiResponse
import com.cryptoemergency.keineexchange.navigation.Routes
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.repository.requests.common.ErrorResponse
import com.cryptoemergency.keineexchange.repository.store.data.OffersResponse

@Composable
fun VacanciesScreen(
    modifier: Modifier = Modifier,
    vacanciesViewModel: VacanciesViewModel
) {
    val offers by vacanciesViewModel.vacancies.collectAsState()

    when (offers) {
        is ApiResponse.Success -> {
            val data = (offers as ApiResponse.Success<OffersResponse>).data.vacancies
            val navController = LocalNavController.current

            LazyColumn(
                modifier = modifier.padding(horizontal = 16.dp),
            ) {
                item { VacanciesInfoScreen(count = data.size) }
                items(data.size) { index ->
                    VacanciesItem(
                        vacancy = data[index],
                        modifier = Modifier.padding(bottom = 16.dp),
                        viewModel = vacanciesViewModel
                    ) {
                        vacanciesViewModel.currentIdVacancy.value = data[index].id
                        vacanciesViewModel.fillItemVacancy(vacanciesViewModel.currentIdVacancy.value!!)
                        navController.navigate(Routes.Vacancy.VacancyItem.route)
                    }
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
fun VacanciesInfoScreen(
    modifier: Modifier = Modifier,
    count: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$count вакансий")
        TextButton(onClick = { /*TODO*/ }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "По соответствию",
                    style = Theme.typography.text1.copy(
                        color = Theme.colors.accentLight
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.filter_sort),
                    contentDescription = null,
                    tint = Theme.colors.accentLight,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}