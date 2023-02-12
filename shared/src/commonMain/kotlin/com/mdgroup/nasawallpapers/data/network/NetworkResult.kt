package com.mdgroup.nasawallpapers.data.network

import io.ktor.http.HttpStatusCode

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val status: HttpStatusCode? = null, val exception: Exception? = null): NetworkResult<Nothing>()
}