package com.mdgroup.nasawallpapers.presentation.screens.about

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mdgroup.nasawallpapers.BuildConfig
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.core.utils.IntentUtils
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.views.HeaderView

@ExperimentalMaterialApi
@Composable
fun AboutScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HeaderView(
            label = stringResource(id = R.string.about_app),
            onBack = { navController.popBackStack() }
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ResourcesCompat.getDrawable(
                    LocalContext.current.resources,
                    R.drawable.ic_launcher_playstore,
                    LocalContext.current.theme
                )?.let { drawable ->
                    val bitmap = Bitmap.createBitmap(
                        drawable.intrinsicWidth, drawable.intrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    drawable.setBounds(0, 0, canvas.width, canvas.height)
                    drawable.draw(canvas)

                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = stringResource(id = R.string.app_icon_content_description),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .width(140.dp)
                            .height(140.dp)
                    )
                }
            }

            item {
                Text(
                    text = "${stringResource(id = R.string.app_name)} - ${stringResource(id = R.string.version)}: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                    modifier = Modifier
                        .padding(top = 28.dp)
                        .padding(horizontal = 16.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.about_app_description),
                    style = TextStyle(color = MaterialTheme.colors.onSecondary),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                )
            }

            item {
                val privacyPolicyUrl = stringResource(id = R.string.privacy_policy_url)

                Text(
                    text = stringResource(id = R.string.legal_information).uppercase(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSecondary
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp, bottom = 8.dp)
                        .fillMaxWidth()
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
                            text = AnnotatedString(stringResource(id = R.string.privacy_policy)),
                            style = TextStyle(
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                            onClick = { IntentUtils.openUrl(context, privacyPolicyUrl) }
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            color = MaterialTheme.colors.onSecondary,
                            thickness = 0.5.dp
                        )
                        ClickableText(
                            text = AnnotatedString(stringResource(id = R.string.licenses)),
                            style = TextStyle(
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                            onClick = { navController.navigate(Router.LICENSES) }
                        )
                    }
                }
            }

            item {
                val githubUrl = stringResource(id = R.string.github_url)

                Card(
                    onClick = { IntentUtils.openUrl(context, githubUrl) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Text(
                        text = stringResource(id = R.string.open_on_github),
                        style = TextStyle(
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                    )
                }
            }

            item {
                val text = stringResource(id = R.string.share_app_description)

                Card(
                    onClick = {
                        IntentUtils.shareApp(context, text)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 64.dp)
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Text(
                        text = stringResource(id = R.string.share_app),
                        style = TextStyle(
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                    )
                }
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
        AboutScreen(rememberNavController())
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        AboutScreen(rememberNavController())
    }
}