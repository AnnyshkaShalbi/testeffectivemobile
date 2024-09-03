package com.cryptoemergency.keineexchange.ui.screens.vacancies

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.keineexchange.api.network.ApiResponse
import com.cryptoemergency.keineexchange.api.network.client
import com.cryptoemergency.keineexchange.repository.requests.common.ErrorResponse
import com.cryptoemergency.keineexchange.repository.store.data.OffersResponse
import com.cryptoemergency.keineexchange.repository.store.data.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import getOffers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VacanciesViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _vacancies = MutableStateFlow<ApiResponse<OffersResponse, ErrorResponse>?>(null)
    val vacancies: StateFlow<ApiResponse<OffersResponse, ErrorResponse>?> = _vacancies

    var currentIdVacancy = mutableStateOf<String?>(null)
    var currentItemVacancy = mutableStateOf<Vacancy?>(null)
    var showModalResponse = mutableStateOf(false)

    var selectedQuestion = mutableStateOf<String?>(null)
    var selectedCandidate = mutableStateOf<String?>(null)
    var responseText = mutableStateOf("")

    fun fillItemVacancy(currentIdVacancy: String) {
        viewModelScope.launch {
            _vacancies.value?.let { apiResponse ->
                if (apiResponse is ApiResponse.Success) {
                    val foundVacancy = apiResponse.data.vacancies.find { it.id == currentIdVacancy }
                    currentItemVacancy.value = foundVacancy
                }
            }
        }
    }

    init {
        fetchOffers()
    }

    fun onChangeResponseValue(newValue: String) {
        responseText.value = newValue
    }

    fun openModalResponse() {
        showModalResponse.value = true
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            _vacancies.value = getOffers(client)
        }
    }

    // Метод для открытия модального окна с передачей выбранного вопроса
    fun openModalResponseQuestion(
        question: String,
        candidate: String
    ) {
        selectedQuestion.value = question
        selectedCandidate.value = candidate
        if(selectedQuestion.value?.isNotEmpty() == true) {
            responseText.value = selectedQuestion.value!!

        }
        showModalResponse.value = true
    }

    // Метод для сброса выбранного вопроса при закрытии модального окна
    fun resetSelectedQuestion() {
        selectedQuestion.value = null
        showModalResponse.value = false
        responseText.value = ""
    }

}