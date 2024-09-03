package com.cryptoemergency.keineexchange.ui.screens.menu.search

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.keineexchange.api.network.ApiResponse
import com.cryptoemergency.keineexchange.api.network.client
import com.cryptoemergency.keineexchange.repository.requests.common.ErrorResponse
import com.cryptoemergency.keineexchange.repository.store.data.OffersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import getOffers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    var search = mutableStateOf("")

    fun onChangeSearchValue(newValue: String) {
        search.value = newValue
    }

    private val _offers = MutableStateFlow<ApiResponse<OffersResponse, ErrorResponse>?>(null)
    val offers: StateFlow<ApiResponse<OffersResponse, ErrorResponse>?> = _offers

    init {
        fetchOffers()
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            _offers.value = getOffers(client)
        }
    }
}