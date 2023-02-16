package com.mdgroup.nasawallpapers.presentation.views

import android.content.res.Configuration
import android.view.ContextThemeWrapper
import android.widget.RatingBar
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme

@Composable
fun RatingBarView(modifier: Modifier = Modifier, ratingChange: (Float) -> Unit = {}) {

    AndroidView(
        modifier = modifier.wrapContentSize(),
        factory = { context ->
            // Unfortunately this cannot be customized by Jetpack Compose
            RatingBar(ContextThemeWrapper(context, R.style.RatingBar))
        },
        update = { view ->
            view.setOnRatingBarChangeListener { _, rating, _ ->
                ratingChange(rating)
            }
        }
    )
}

/** Previews */
@ExperimentalMaterialApi
@Preview(name = "Light mode", showBackground = true)
@Composable
private fun LightPreview() {
    ApplicationTheme {
        RatingBarView()
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        RatingBarView()
    }
}