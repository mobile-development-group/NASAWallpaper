package com.mdgroup.nasawallpapers.presentation.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme

@Composable
fun ErrorView(
    title: String = stringResource(id = R.string.error_title),
    description: String = stringResource(id = R.string.error_description),
    onClickTryAgain: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 64.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = description
            )
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.try_again)),
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 16.dp),
                onClick = onClickTryAgain
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
        ErrorView(onClickTryAgain = {})
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        ErrorView(onClickTryAgain = {})
    }
}