package com.cryptoemergency.keineexchange.repository.requests.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String
)