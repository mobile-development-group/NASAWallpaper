package com.mdgroup.nasawallpapers.data.repositories

import com.mdgroup.nasawallpapers.data.mappers.WallpaperMapper
import com.mdgroup.nasawallpapers.data.network.ApiInterface
import com.mdgroup.nasawallpapers.data.network.NetworkResult
import com.mdgroup.nasawallpapers.data.responses.WallpaperResponse
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.Result
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import com.mdgroup.nasawallpapers.domain.repositories.NasaRepository
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NasaRepositoryImpl(private val api: ApiInterface) : NasaRepository {

    override suspend fun fetch(date: DateModel): Result<WallpaperModel> {
        return when (val result = api.getWallpaper(date)) {
            is NetworkResult.Success -> {
                //                val response = result.data.body<WallpaperResponse>()
                val response = Json.decodeFromString<WallpaperResponse>(result.data.bodyAsText())
                val data = WallpaperMapper.responseToModel(response)
                Result.success(data)
            }
            is NetworkResult.Error -> {
                Result.error(result.exception)
            }
        }
    }
}