package com.mdgroup.nasawallpapers.domain.repositories

import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import com.mdgroup.nasawallpapers.domain.models.Result

interface NasaRepository {

    @Throws(Exception::class)
    suspend fun fetch(date: DateModel): Result<WallpaperModel>

    @Throws(Exception::class)
    suspend fun fetch(from: DateModel, to: DateModel): Result<List<WallpaperModel>>
}