package com.mdgroup.nasawallpapers.presentation.screens.more

import androidx.annotation.DrawableRes

data class MoreAppModel(
    val title: String,
    val id: String,
    val descriptor: String,
    @DrawableRes
    val icon: Int
)
