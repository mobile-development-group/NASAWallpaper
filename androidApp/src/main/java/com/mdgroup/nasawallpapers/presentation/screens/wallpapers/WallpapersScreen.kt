package com.mdgroup.nasawallpapers.presentation.screens.wallpapers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mdgroup.nasawallpapers.presentation.extensions.items
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpapersViewModel
import com.mdgroup.nasawallpapers.presentation.views.ErrorView
import org.koin.androidx.compose.viewModel

@Composable
fun WallpapersScreen(navController: NavHostController) {

    val viewModel: WallpapersViewModel by viewModel()
    val wallpapers = viewModel.wallpapers.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(items = wallpapers) { item, _ ->
                    WallpaperItem(
                        modifier = Modifier.clickable {
                            navController.navigate(Router.WALLPAPER + "/${item.date}")
                        },
                        wallpaper = item
                    )
                }
            }
        }

        when (wallpapers.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
            }
            is LoadState.Error -> {
                ErrorView(onClickTryAgain = {})
            }
        }
    }
}