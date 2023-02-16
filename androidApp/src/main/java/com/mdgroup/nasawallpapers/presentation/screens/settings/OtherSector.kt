package com.mdgroup.nasawallpapers.presentation.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdgroup.nasawallpapers.BuildConfig
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme

@Composable
fun OtherSector(
    onClickWriteToDeveloper: () -> Unit = {},
    onClickRateApp: () -> Unit = {},
    onClickAboutApp: () -> Unit = {},
    onClickMoreApps: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.other).uppercase(),
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSecondary
            ),
            modifier = Modifier.padding(start = 32.dp, bottom = 8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Column {
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.report_error)),
                    style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                    onClick = { onClickWriteToDeveloper() }
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    color = MaterialTheme.colors.onSecondary,
                    thickness = 0.5.dp
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.rate_app)),
                    style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                    onClick = { onClickRateApp() }
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    color = MaterialTheme.colors.onSecondary,
                    thickness = 0.5.dp
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.about_app)),
                    style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                    onClick = { onClickAboutApp() }
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    color = MaterialTheme.colors.onSecondary,
                    thickness = 0.5.dp
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.more_apps)),
                    style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                    onClick = { onClickMoreApps() }
                )
            }
        }
        Text(
            text = "${stringResource(id = R.string.version)}: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})${if (BuildConfig.DEBUG) " - DEBUG" else ""}",
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSecondary
            ),
            modifier = Modifier.padding(start = 32.dp, top = 8.dp, end = 32.dp)
        )
    }
}

/** Previews */
@Preview(showBackground = true)
@Composable
private fun OtherSectorPreview() {
    ApplicationTheme {
        OtherSector()
    }
}