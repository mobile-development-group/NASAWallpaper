package com.mdgroup.nasawallpapers.data.network

import com.mdgroup.nasawallpapers.domain.models.DateModel
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiProvider(private val httpClient: HttpClient) : ApiInterface {

    override suspend fun getWallpaper(dateModel: DateModel): NetworkResult<HttpResponse> {
        try {
            val response: HttpResponse = httpClient.request {
                url("https://api.nasa.gov/planetary/apod")
                method = HttpMethod.Get
                parameter("api_key","GnX1xqVy5HZaKZVXUiavt9g4PcVQHkc24Qddc4cO")
                parameter("date","${dateModel.year}-${dateModel.month}-${dateModel.day}")
                contentType(ContentType.Application.Json)
            }

            return if (response.status == HttpStatusCode.OK) {
                NetworkResult.Success(response)
            } else {
                NetworkResult.Error(status = response.status)
            }
        } catch (ex: Exception) {
            return NetworkResult.Error(exception = ex)
        }
    }
}