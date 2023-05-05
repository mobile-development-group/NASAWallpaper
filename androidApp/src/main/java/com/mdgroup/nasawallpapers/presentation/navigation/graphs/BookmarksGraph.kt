package com.mdgroup.nasawallpapers.presentation.navigation.graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.screens.bookmarks.BookmarksScreen
import com.mdgroup.nasawallpapers.presentation.screens.wallpapers.WallpaperScreen

@ExperimentalMaterialApi
@Composable
fun BookmarksGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Router.WALLPAPERS) {
        composable(route = Router.WALLPAPERS) {
            BookmarksScreen(navController)
        }
        composable(
            route = Router.WALLPAPER + "/{date}",
            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) {
            WallpaperScreen(navController, it.arguments?.getString("date"))
        }
    }
}