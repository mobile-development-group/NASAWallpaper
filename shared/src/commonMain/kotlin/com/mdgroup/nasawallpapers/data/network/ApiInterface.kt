package com.mdgroup.nasawallpapers.data.network

import com.mdgroup.nasawallpapers.domain.models.DateModel
import io.ktor.client.statement.*

interface ApiInterface {

    suspend fun getWallpaper(date: DateModel): NetworkResult<HttpResponse>

    suspend fun getWallpapers(from: DateModel, to: DateModel): NetworkResult<HttpResponse>
}