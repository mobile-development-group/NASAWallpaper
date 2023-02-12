package com.mdgroup.nasawallpapers.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DateModel(
    val year: Int,
    val month: Int,
    val day: Int
)