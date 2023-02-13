package com.mdgroup.nasawallpapers.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class WallpaperModel(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val hdurl: String,
    val mediaType: String?,
    val serviceVersion: String?,
    val title: String,
    val url: String?,

    val uri: String? = null
)