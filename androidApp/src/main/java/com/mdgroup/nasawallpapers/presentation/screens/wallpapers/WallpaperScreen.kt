package com.mdgroup.nasawallpapers.presentation.screens.wallpapers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WallpaperScreen(navController: NavHostController, date: String?) {

    val imageLoader: ImageLoader by inject()

    val viewModel: WallpaperViewModel by viewModel {
        parametersOf(date)
    }
    val state = viewModel.state
    Box {
        state.wallpaper?.let { wallpaper ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 64.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    AsyncImage(
                        model = wallpaper.url,
                        contentDescription = wallpaper.title,
                        imageLoader = imageLoader,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                wallpaper.title?.let {
                    item {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier
                                .padding(top = 28.dp)
                                .padding(horizontal = 16.dp),
                        )
                    }
                }

                wallpaper.date?.let {
                    item {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .padding(horizontal = 16.dp),
                        )
                    }
                }

                wallpaper.explanation?.let {
                    item {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }
                }

                wallpaper.copyright?.let {
                    item {
                        Text(
                            text = "@ $it",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
            }
        }

        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_content_description),
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}