package com.mdgroup.nasawallpapers.domain.repositories

import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.Result
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel

interface WallpaperRepository {

    @Throws(Exception::class)
    suspend fun fetch(date: DateModel): Result<WallpaperModel>

    @Throws(Exception::class)
    suspend fun fetch(from: DateModel, to: DateModel): Result<List<WallpaperModel>>

    fun getAll(): List<WallpaperModel>

    fun getByDate(date: DateModel): WallpaperModel?

    fun save(model: WallpaperModel)

    fun delete(date: DateModel)

    fun clear()
}