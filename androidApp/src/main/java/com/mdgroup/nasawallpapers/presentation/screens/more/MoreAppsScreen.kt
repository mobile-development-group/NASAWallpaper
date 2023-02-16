package com.mdgroup.nasawallpapers.presentation.screens.more

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.core.utils.IntentUtils
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme
import com.mdgroup.nasawallpapers.presentation.views.HeaderView

@ExperimentalMaterialApi
@Composable
fun MoreAppsScreen(navController: NavHostController) {

    val context = LocalContext.current

    val apps = listOf(
        MoreAppModel(
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
        MoreAppModel(
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
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderView(
            label = stringResource(id = R.string.more_apps),
            onBack = { navController.popBackStack() }
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = apps) { index, item ->
                MoreAppItem(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = if (index == apps.size - 1) 16.dp else 0.dp
                    ),
                    app = item,
                    onClick = {
                        IntentUtils.openMarket(context, item.id)
                    }
                )
            }
        }
    }
}

/** Previews */
@ExperimentalMaterialApi
@Preview(name = "Light mode", showBackground = true)
@Composable
private fun LightPreview() {
    ApplicationTheme {
        MoreAppsScreen(rememberNavController())
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        MoreAppsScreen(rememberNavController())
    }
}