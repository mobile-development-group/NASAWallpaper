package com.mdgroup.nasawallpapers.presentation.screens.settings

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mdgroup.nasawallpapers.presentation.BottomSheetController
import com.mdgroup.nasawallpapers.presentation.navigation.Router

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
    }
}