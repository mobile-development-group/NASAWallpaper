package com.mdgroup.nasawallpapers.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mdgroup.nasawallpapers.presentation.navigation.GraphFactory
import com.mdgroup.nasawallpapers.presentation.navigation.NavigationItem
import com.mdgroup.nasawallpapers.presentation.screens.wallpapers.WallpapersGraph

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {

    var currentTab by mutableStateOf(
        NavigationItem.Wallpapers.route
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(currentTab) { route ->
                    currentTab = route
                }
            }
        ) {
            Navigation(route = currentTab)
        }
    }

    @Composable
    private fun Navigation(route: String) {
        val settingsNavState = rememberSaveable { mutableStateOf(Bundle()) }

        // Надстройка статуса для соответствия iOS
        if (!isSystemInDarkTheme())
            window.statusBarColor = MaterialTheme.colors.primary.toArgb()

        when (route) {
            NavigationItem.Wallpapers.route -> {
                GraphFactory(navState = settingsNavState) { navController ->
                    WallpapersGraph(navController)
                }
            }
            //            NavigationItem.Saved.route ->
            //            NavigationItem.Settings.route -> {
            //                // Надстройка статуса для соответствия iOS
            //                if (!isSystemInDarkTheme())
            //                    window.statusBarColor = MaterialTheme.colors.background.toArgb()
            //
            //                GraphFactory(navState = settingsNavState) { navController ->
            //                    SettingsGraph(navController, bottomSheetController)
            //                }
            //            }
        }
    }

    @Composable
    fun BottomNavigationBar(route: String, onClick: (String) -> Unit) {
        val items = listOf(
            NavigationItem.Wallpapers,
            NavigationItem.Saved,
            NavigationItem.Settings
        )

        val modifier = if (!isSystemInDarkTheme() && route == NavigationItem.Wallpapers.route) {
            Modifier.background(MaterialTheme.colors.primary)
        } else {
            Modifier.background(MaterialTheme.colors.background)
        }

        BottomNavigation {
            items.forEach { item ->
                val isSelected = item.route == route
                BottomNavigationItem(
                    modifier = modifier,
                    selected = isSelected,
                    icon = {
                        Icon(
                            painterResource(id = if (isSelected) item.selectedIcon else item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(text = item.title) },
                    selectedContentColor = MaterialTheme.colors.primaryVariant,
                    unselectedContentColor = MaterialTheme.colors.onSecondary,
                    alwaysShowLabel = true,
                    onClick = {
                        onClick.invoke(item.route)
                    }
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@Preview
@Composable
fun DefaultPreview() {
    ApplicationTheme {
        MainActivity()
    }
}
