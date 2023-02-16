package com.mdgroup.nasawallpapers.presentation.sheets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme
import com.mdgroup.nasawallpapers.presentation.views.RatingBarView

@ExperimentalMaterialApi
@Composable
fun RatingView(ratingSet: (Float) -> Unit) {
    var rating = 0F

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RatingBarView(
            modifier = Modifier.padding(top = 32.dp),
            ratingChange = {
                rating = it
            }
        )

        Card(
            onClick = {
                ratingSet(rating)
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Text(
                text = stringResource(id = R.string.rate),
                style = TextStyle(
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            )
        }
    }
}

/** Previews */
@ExperimentalMaterialApi
@Preview(name = "Light mode", showBackground = true)
@Composable
private fun LightPreview() {
    ApplicationTheme {
        RatingView {}
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        RatingView {}
    }
}