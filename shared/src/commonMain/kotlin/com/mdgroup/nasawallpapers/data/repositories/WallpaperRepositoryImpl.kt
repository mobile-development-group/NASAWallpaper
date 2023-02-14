package com.mdgroup.nasawallpapers.data.repositories

import com.mdgroup.nasawallpapers.core.platform.Logger
import com.mdgroup.nasawallpapers.data.mappers.WallpaperMapper
import com.mdgroup.nasawallpapers.data.network.ApiInterface
import com.mdgroup.nasawallpapers.data.network.NetworkResult
import com.mdgroup.nasawallpapers.data.responses.WallpaperResponse
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.Result
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import com.mdgroup.nasawallpapers.domain.repositories.WallpaperRepository
import com.mdgroup.nasawallpapers.sqldelight.Database
import com.mdgroup.nasawallpapers.sqldelight.WallpaperQueries
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WallpaperRepositoryImpl(private val api: ApiInterface, private val database: Database) : WallpaperRepository {

    private val queries: WallpaperQueries = database.wallpaperQueries

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

    override suspend fun fetch(from: DateModel, to: DateModel): Result<List<WallpaperModel>> {
        return when (val result = api.getWallpapers(from, to)) {
            is NetworkResult.Success -> {
                val response = result.data.body<List<WallpaperResponse>>()
                val data = response.map { WallpaperMapper.responseToModel(it) }.sortedByDescending { it.date }
                Result.success(data)
            }
            is NetworkResult.Error -> {
                Result.error(result.exception)
            }
        }
    }

    override fun getAll(): List<WallpaperModel> =
        queries.selectAll().executeAsList().map { WallpaperMapper.entityToModel(it) }

    override fun getByDate(date: DateModel): WallpaperModel? {
        val result = queries.selectByDate(date.toString()).executeAsOneOrNull()
        return if (result != null) {
            Logger.d("Wallpaper by $date returned from database")
            WallpaperMapper.entityToModel(result)
        } else {
            return null
        }
    }

    override fun save(model: WallpaperModel) {
        queries.delete(model.date)
        queries.insert(
            model.copyright,
            model.date,
            model.explanation,
            model.hdurl ?: "",
            model.mediaType,
            model.serviceVersion,
            model.title ?: "",
            model.url,
            model.uri ?: ""
        )

        Logger.d("Wallpaper for date: ${model.date} SAVED")
    }

    override fun delete(date: DateModel) {
        queries.delete(date.toString())
    }

    override fun clear() {
        queries.clear()
    }
}