package com.mdgroup.nasawallpapers.presentation.screens.wallpapers

import android.app.WallpaperManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WallpaperScreen(navController: NavHostController, date: String?) {

    val context = LocalContext.current

    val viewModel: WallpaperViewModel by viewModel {
        parametersOf(date)
    }
    val state = viewModel.state

    Box {
        state.wallpaper?.let { wallpaper ->
            WallpaperDetailsView(
                context = context,
                wallpaper = wallpaper,
                onClickSave = { viewModel.save(context) },
                onClickShare = { viewModel.share(context) },
                onClickAsWallpaper = {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    viewModel.setAsWallpaper(context, wallpaperManager)
                }
            )
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
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}