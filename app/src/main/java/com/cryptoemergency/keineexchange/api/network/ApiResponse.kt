package com.cryptoemergency.keineexchange.api.network

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

sealed interface ApiResponse<out Success, out Error> {
    data class Success<out Success>(
        val status: HttpStatusCode,
        val data: Success,
        val headers: Headers,
    ) : ApiResponse<Success, Nothing>

    data class Error<out Error>(
        val status: HttpStatusCode,
        val data: Error? = null,
        val headers: Headers? = null,
    ) : ApiResponse<Nothing, Error>
}
