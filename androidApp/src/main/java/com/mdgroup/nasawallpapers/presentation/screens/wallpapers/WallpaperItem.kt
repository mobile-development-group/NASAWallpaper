package com.mdgroup.nasawallpapers.presentation.screens.wallpapers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import org.koin.androidx.compose.inject

@Composable
internal fun WallpaperItem(modifier: Modifier = Modifier, wallpaper: WallpaperModel) {

    val imageLoader: ImageLoader by inject()

    Card(
        modifier = modifier.height(170.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp
    ) {
        AsyncImage(
            model = wallpaper.url,
            contentDescription = wallpaper.title,
            imageLoader = imageLoader,
            modifier = Modifier.clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        wallpaper.date?.let {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = it,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.secondary),
                )
            }
        }
    }
}