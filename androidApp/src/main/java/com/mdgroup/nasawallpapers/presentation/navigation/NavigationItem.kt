package com.mdgroup.nasawallpapers.presentation.navigation

import com.mdgroup.nasawallpapers.R

sealed class NavigationItem(var route: String, var icon: Int, var selectedIcon: Int, var title: String) {
    object Wallpapers : NavigationItem(Router.WALLPAPERS, R.drawable.ic_globe, R.drawable.ic_globe_fill, "Wallpapers")

    object Calendar : NavigationItem(Router.CALENDAR, R.drawable.ic_calendar, R.drawable.ic_calendar, "Calendar")

    object Bookmarks : NavigationItem(Router.BOOKMARKS, R.drawable.ic_bookmark, R.drawable.ic_bookmark_fill, "Bookmarks")

    object Settings : NavigationItem(Router.SETTINGS, R.drawable.ic_gear, R.drawable.ic_gear, "Settings")
}