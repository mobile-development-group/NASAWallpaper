package com.mdgroup.nasawallpapers.data.mappers

import com.mdgroup.nasawallpapers.data.responses.WallpaperResponse
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel

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
}