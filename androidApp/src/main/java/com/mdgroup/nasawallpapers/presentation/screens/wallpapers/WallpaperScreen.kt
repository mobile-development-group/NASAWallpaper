package com.mdgroup.nasawallpapers.presentation.screens.wallpapers

import android.app.WallpaperManager
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.core.utils.FileUtils
import com.mdgroup.nasawallpapers.core.utils.nullIfEmpty
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WallpaperScreen(navController: NavHostController, date: String?) {

    val context = LocalContext.current

    val imageLoader: ImageLoader by inject()

    val viewModel: WallpaperViewModel by viewModel {
        parametersOf(date)
    }
    val state = viewModel.state
    val isSaved = state.wallpaper?.uri != null

    Box {
        state.wallpaper?.let { wallpaper ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 56.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    wallpaper.uri?.let { uri ->
                        FileUtils.bitmapFromUri(context, Uri.parse(uri))?.asImageBitmap()?.let {
                            Image(
                                bitmap = it,
                                contentDescription = wallpaper.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    } ?: run {
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
                }

                wallpaper.title.nullIfEmpty()?.let {
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

                item {
                    Text(
                        text = wallpaper.date,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .padding(horizontal = 16.dp),
                    )
                }

                item {
                    Row(modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp)
                        .height(32.dp)
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colors.primaryVariant)
                        } else {
                            IconButton(onClick = {
                                viewModel.save(context)
                            }) {
                                Icon(
                                    painterResource(id = if (isSaved) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark),
                                    contentDescription = stringResource(id = R.string.save_content_description),
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colors.primaryVariant
                                )
                            }
                            IconButton(onClick = {
                                viewModel.share(context)
                            }) {
                                Icon(
                                    painterResource(id = R.drawable.ic_share),
                                    contentDescription = stringResource(id = R.string.share_content_description),
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colors.primaryVariant
                                )
                            }
                            IconButton(onClick = {
                                val wallpaperManager = WallpaperManager.getInstance(context)
                                viewModel.setAsWallpaper(context, wallpaperManager)
                            }) {
                                Icon(
                                    painterResource(id = R.drawable.ic_phone_fill),
                                    contentDescription = stringResource(id = R.string.as_wallpaper_content_description),
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colors.primaryVariant
                                )
                            }
                        }
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
                            text = "Copyright belongs to $it",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
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