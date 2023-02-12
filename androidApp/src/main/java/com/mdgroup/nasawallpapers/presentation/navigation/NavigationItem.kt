package com.mdgroup.nasawallpapers.presentation.navigation

import com.mdgroup.nasawallpapers.R

sealed class NavigationItem(var route: String, var icon: Int, var selectedIcon: Int, var title: String) {
    object Wallpapers : NavigationItem(Router.WALLPAPERS, R.drawable.ic_globe, R.drawable.ic_globe_fill, "Wallpapers")
    object Saved : NavigationItem(Router.SAVED, R.drawable.ic_folder, R.drawable.ic_folder_fill, "Saved")
    object Settings : NavigationItem(Router.SETTINGS, R.drawable.ic_gear, R.drawable.ic_gear, "Settings")
}