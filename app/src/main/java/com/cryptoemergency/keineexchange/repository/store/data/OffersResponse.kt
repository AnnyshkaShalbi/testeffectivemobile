package com.cryptoemergency.keineexchange.repository.store.data

import kotlinx.serialization.Serializable

@Serializable
data class OffersResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)

@Serializable
data class Offer(
    val id: String? = null,
    val title: String,
    val link: String,
    val button: Button? = null
)

@Serializable
data class Button(
    val text: String
)

@Serializable
data class Vacancy(
    val id: String,
    val lookingNumber: Int? = 0,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int? = 0,
    val description: String = "",
    val responsibilities: String,
    val questions: List<String>
)


@Serializable
data class Address(
    val town: String,
    val street: String,
    val house: String
)

@Serializable
data class Experience(
    val previewText: String,
    val text: String
)

@Serializable
data class Salary(
    val full: String? = null,
    val short: String? = null
)