package com.mdgroup.nasawallpapers.data.mappers

import com.mdgroup.nasawallpapers.data.responses.WallpaperResponse
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import com.mdgroup.nasawallpapers.sqldelight.WallpaperEntity

object WallpaperMapper {

    fun responseToModel(response: WallpaperResponse): WallpaperModel {
        return WallpaperModel(
            response.copyright,
            response.date,
            response.explanation,
            response.hdurl ?: "",
            response.mediaType,
            response.serviceVersion,
            response.title ?: "",
            response.url
        )
    }

    fun entityToModel(entity: WallpaperEntity): WallpaperModel {
        return WallpaperModel(
            entity.copyright,
            entity.date,
            entity.explanation,
            entity.hdurl ?: "",
            entity.mediaType,
            entity.serviceVersion,
            entity.title ?: "",
            entity.url,
            entity.uri
        )
    }
}