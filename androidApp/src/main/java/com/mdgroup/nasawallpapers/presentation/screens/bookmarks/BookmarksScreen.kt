package com.mdgroup.nasawallpapers.presentation.screens.bookmarks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.screens.wallpapers.WallpaperItem
import com.mdgroup.nasawallpapers.presentation.viewmodels.BookmarksViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun BookmarksScreen(navController: NavHostController) {

    val viewModel: BookmarksViewModel by viewModel()
    val bookmarks = viewModel.state.bookmarks

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
                items(count = bookmarks.count()) { index ->
                    WallpaperItem(
                        modifier = Modifier.clickable {
                            navController.navigate(Router.WALLPAPER + "/${bookmarks[index].date}")
                        },
                        wallpaper = bookmarks[index]
                    )
                }
            }
        }
    }
}