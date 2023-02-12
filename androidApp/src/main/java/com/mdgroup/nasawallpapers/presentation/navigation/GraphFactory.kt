package com.mdgroup.nasawallpapers.presentation.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun GraphFactory(
    navState: MutableState<Bundle>,
    content: @Composable (NavHostController) -> Unit
) {
    val navController = rememberNavController()

    DisposableEffect(Unit) {
        val callback = NavController.OnDestinationChangedListener { navController, _, _ ->
            navState.value = navController.saveState() ?: Bundle()
        }
        navController.addOnDestinationChangedListener(callback)
        navController.restoreState(navState.value)

        onDispose {
            navController.removeOnDestinationChangedListener(callback)
            navController.enableOnBackPressed(false)
        }
    }
    content(navController)
}