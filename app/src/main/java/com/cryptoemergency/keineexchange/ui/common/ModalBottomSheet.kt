package com.cryptoemergency.keineexchange.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.keineexchange.R
import com.cryptoemergency.keineexchange.providers.theme.Theme
import com.cryptoemergency.keineexchange.ui.common.inputs.Input
import com.cryptoemergency.keineexchange.ui.screens.vacancies.VacanciesViewModel

// модальное меню выезжающее снизу
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        containerColor = Theme.colors.background,
        modifier = Modifier.wrapContentHeight(),
    ) {
        BottomSheetContent() {
            content()
        }
    }
}

// контентная часть ModalBottomSheet
@Composable
private fun BottomSheetContent(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        content()
    }
}

// условие вызова модального окна
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConditionModalBottom(
    viewModel: VacanciesViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    if(viewModel.showModalResponse.value) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        CustomModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss
        ) {
            content()
        }
    }
}


