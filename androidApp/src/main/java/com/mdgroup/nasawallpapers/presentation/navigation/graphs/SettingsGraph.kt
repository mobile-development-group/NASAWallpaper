package com.mdgroup.nasawallpapers.presentation.navigation.graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mdgroup.nasawallpapers.presentation.BottomSheetController
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.screens.about.AboutScreen
import com.mdgroup.nasawallpapers.presentation.screens.licenses.LicensesScreen
import com.mdgroup.nasawallpapers.presentation.screens.more.MoreAppsScreen
import com.mdgroup.nasawallpapers.presentation.screens.settings.SettingsScreen

@ExperimentalMaterialApi
@Composable
fun SettingsGraph(navController: NavHostController, bottomSheetController: BottomSheetController) {
    NavHost(navController, startDestination = Router.SETTINGS) {

        composable(route = Router.SETTINGS) {
            SettingsScreen(navController, bottomSheetController)
        }

        composable(route = Router.ABOUT_APP) {
            AboutScreen(navController)
        }

        composable(route = Router.LICENSES) {
            LicensesScreen(navController)
        }

        composable(route = Router.MORE_APPS) {
            MoreAppsScreen(navController)
        }

    }
}