package com.mdgroup.nasawallpapers.domain.interactors

import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.Result
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import com.mdgroup.nasawallpapers.domain.repositories.WallpaperRepository

class WallpaperInteractorImpl(private val repository: WallpaperRepository) : WallpaperInteractor {

    override suspend fun fetch(date: DateModel): Result<WallpaperModel> {
        val cache = repository.getByDate(date)
        return if (cache != null) {
            Result.success(cache)
        } else {
            repository.fetch(date)
        }
    }

    override suspend fun fetch(from: DateModel, to: DateModel): Result<List<WallpaperModel>> = repository.fetch(from, to)

    override fun getAll(): List<WallpaperModel> = repository.getAll()

    override fun save(model: WallpaperModel) {
        repository.save(model)
    }

    override fun delete(date: DateModel) {
        repository.delete(date)
    }

    override fun clear() {
        repository.clear()
    }
}