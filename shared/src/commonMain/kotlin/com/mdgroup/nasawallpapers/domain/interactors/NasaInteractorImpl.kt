package com.mdgroup.nasawallpapers.domain.interactors

import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.Result
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import com.mdgroup.nasawallpapers.domain.repositories.NasaRepository

class NasaInteractorImpl(private val repository: NasaRepository) : NasaInteractor {

    override suspend fun fetch(date: DateModel): Result<WallpaperModel> = repository.fetch(date)
    override suspend fun fetch(from: DateModel, to: DateModel): Result<List<WallpaperModel>> = repository.fetch(from, to)
}