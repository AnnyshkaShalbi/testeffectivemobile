import com.cryptoemergency.keineexchange.api.network.ApiResponse
import com.cryptoemergency.keineexchange.api.network.json
import com.cryptoemergency.keineexchange.repository.requests.common.ErrorResponse
import com.cryptoemergency.keineexchange.repository.store.data.OffersResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.net.UnknownHostException

suspend fun getOffers(client: HttpClient): ApiResponse<OffersResponse, ErrorResponse> {
    return try {
        val response: HttpResponse = client.get("https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
        val responseBody = response.body<String>()

        if (response.status.isSuccess()) {
            ApiResponse.Success(
                status = response.status,
                headers = response.headers,
                data = json.decodeFromString<OffersResponse>(responseBody),
            )
        } else {
            ApiResponse.Error(
                status = response.status,
                headers = response.headers,
                data = json.decodeFromString<ErrorResponse>(responseBody),
            )
        }
    } catch (e: SerializationException) {
        ApiResponse.Error(
            status = HttpStatusCode(-1, e.message ?: "SerializationException"),
            data = ErrorResponse(message = e.message ?: "Serialization error")
        )
    } catch (e: ServerResponseException) {
        ApiResponse.Error(
            status = HttpStatusCode(500, e.message),
            data = ErrorResponse(message = e.message ?: "Server error")
        )
    } catch (e: UnknownHostException) {
        ApiResponse.Error(
            status = HttpStatusCode(-1000, e.message ?: "No internet connection"),
            data = ErrorResponse(message = e.message ?: "No internet connection")
        )
    } catch (e: IOException) {
        ApiResponse.Error(
            status = HttpStatusCode(-900, e.message ?: "IO Exception"),
            data = ErrorResponse(message = e.message ?: "IO error")
        )
    }
}