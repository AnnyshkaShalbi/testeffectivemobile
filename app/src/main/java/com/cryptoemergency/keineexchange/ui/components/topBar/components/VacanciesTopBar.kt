package com.cryptoemergency.keineexchange.ui.components.topBar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.providers.localNavController.LocalNavController
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.common.inputs.ValidateInput

@Composable
fun VacanciesTopBar(modifier: Modifier = Modifier) {
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val textState = remember { mutableStateOf("") }
    val navController = LocalNavController.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ValidateInput(
            modifier = Modifier.weight(1f),
            placeholder = "Поиск по вакансиям",
            textState = textState,
            onValidate = { /*TODO*/ },
            leadingIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = null,
                        tint = Theme.colors.grayRegular
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            errorMessage = errorMessage
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