package com.mdgroup.nasawallpapers.presentation.screens.more

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme

@ExperimentalMaterialApi
@Composable
fun MoreAppItem(modifier: Modifier = Modifier, app: MoreAppModel, onClick: (Int) -> Unit = {}) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Image(
                    painter = painterResource(id = app.icon),
                    contentDescription = app.title,
                    modifier = Modifier.clip(MaterialTheme.shapes.small)
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = app.title)
                    ClickableText(
                        text = AnnotatedString(stringResource(id = R.string.open_in_store)),
                        style = TextStyle(
                            color = MaterialTheme.colors.primaryVariant,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        onClick = onClick
                    )
                }
            }

            Text(modifier = Modifier.padding(top = 16.dp), text = app.descriptor)
        }
    }
}

/** Previews */
@ExperimentalMaterialApi
@Preview(name = "Light mode", showBackground = true)
@Composable
private fun LightPreview() {
    ApplicationTheme {
        MoreAppItem(
            app = MoreAppModel(
                title = "Social Media Downloader",
                id = "com.mdgroup.socialmedia",
                descriptor = """
                    Functionality:

                    Download videos from TikTok
                    Download photos, reels, stories from Instagram
                    Download videos, photos and albums from Vkontakte
                    Download videos, photos and animations from internet sites
                    Search for videos, photos and Vkontakte albums
                    Secure authorization
                """.trimIndent(),
                icon = R.drawable.social_media_downloader
            ),
            onClick = {}
        )
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        MoreAppItem(
            app = MoreAppModel(
                title = "AdStat",
                id = "com.mdgroup.adstat",
                descriptor = """
                    AdStat is the best app to track your ads earnings!

                    The application is intended for developers or advertisers. This is a safe and secure way to view your ads earnings. It uses Google's secure login and does not save your password or any personal information. You can perform simple data analysis and view it in simple reports and charts.

                    Functions – Supported data sources from the AdMob Reports API.

                    Earnings reports for today, yesterday, this week, last month and for any selected period
                    Application and banner reports
                    Income charts
                    Dark mode
                    Different currencies
                    IMPORTANT: This is NOT an official AdMob viewer app. But it uses the official API for both sources of income. Please compare the data in this app with yours and report any issue you see.
                """.trimIndent(),
                icon = R.drawable.adstat
            ),
            onClick = {}
        )
    }
}