package com.mdgroup.nasawallpapers.presentation.screens.wallpapers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpapersViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun WallpapersScreen(navController: NavHostController) {

    val viewModel: WallpapersViewModel by viewModel()
    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxHeight()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(state.wallpapers.size) { index ->
                    WallpaperItem(
                        modifier = Modifier.clickable {
                            navController.navigate(Router.WALLPAPER + "/${state.wallpapers[index].date}")
                        },
                        wallpaper = state.wallpapers[index],
                        isLast = state.wallpapers.lastIndex == index
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
        }
    }
}