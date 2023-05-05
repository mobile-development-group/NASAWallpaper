package com.mdgroup.nasawallpapers.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mdgroup.nasawallpapers.presentation.navigation.GraphFactory
import com.mdgroup.nasawallpapers.presentation.navigation.NavigationItem
import com.mdgroup.nasawallpapers.presentation.navigation.graphs.BookmarksGraph
import com.mdgroup.nasawallpapers.presentation.navigation.graphs.SettingsGraph
import com.mdgroup.nasawallpapers.presentation.navigation.graphs.WallpapersGraph
import com.mdgroup.nasawallpapers.presentation.screens.calendar.CalendarScreen

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    var currentTab by mutableStateOf(
        NavigationItem.Wallpapers.route
    )

    /**
     * Content for BottomSheet
     */
    private val content: (@Composable BoxScope.() -> Unit) = {}
    private var bottomSheetContent by mutableStateOf(content)
    lateinit var bottomSheetController: BottomSheetController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                ModalBottomSheet()
            }
        }
    }

    /**
     * For showing BottomSheet
     */
    @Composable
    fun ModalBottomSheet() {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

        bottomSheetController =
            BottomSheetController(rememberCoroutineScope(), { bottomSheetContent = it }, sheetState)

        ModalBottomSheetLayout(
            sheetContent = {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .defaultMinSize(minHeight = 1.dp)
                        .padding(bottom = 60.dp, top = 16.dp),
                    content = bottomSheetContent
                )
            },
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetState = sheetState,
            sheetBackgroundColor = MaterialTheme.colors.background,
        ) {
            // Main screen
            MainScreen(bottomSheetController)
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen(bottomSheetController: BottomSheetController) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(currentTab) { route ->
                    currentTab = route
                }
            }
        ) {
            Navigation(route = currentTab, bottomSheetController)
        }
    }

    @Composable
    private fun Navigation(route: String, bottomSheetController: BottomSheetController) {
        val settingsNavState = rememberSaveable { mutableStateOf(Bundle()) }

        // Config for correspondence with iOS
        //        if (!isSystemInDarkTheme())
        //            window.statusBarColor = MaterialTheme.colors.primary.toArgb()

        when (route) {
            NavigationItem.Wallpapers.route -> {
                GraphFactory(navState = settingsNavState) { navController ->
                    WallpapersGraph(navController)
                }
            }
            NavigationItem.Calendar.route -> {
                CalendarScreen()
            }
            NavigationItem.Bookmarks.route -> {
                GraphFactory(navState = settingsNavState) { navController ->
                    BookmarksGraph(navController)
                }
            }
            NavigationItem.Settings.route -> {
                //                // Надстройка статуса для соответствия iOS
                //                if (!isSystemInDarkTheme())
                //                    window.statusBarColor = MaterialTheme.colors.background.toArgb()
                //
                GraphFactory(navState = settingsNavState) { navController ->
                    SettingsGraph(navController, bottomSheetController)
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(route: String, onClick: (String) -> Unit) {
        val items = listOf(
            NavigationItem.Wallpapers,
            NavigationItem.Calendar,
            NavigationItem.Bookmarks,
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
