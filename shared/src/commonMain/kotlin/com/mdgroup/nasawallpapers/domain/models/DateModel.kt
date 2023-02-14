package com.mdgroup.nasawallpapers.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DateModel(
    val year: Int,
    val month: Int,
    val day: Int
) {
    override fun toString(): String {
        return "$year-${if (month < 10) "0$month" else month}-${if (day < 10) "0$day" else day}"
    }
}