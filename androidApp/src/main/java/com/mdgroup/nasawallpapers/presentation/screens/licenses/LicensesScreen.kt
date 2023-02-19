package com.mdgroup.nasawallpapers.presentation.screens.licenses

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.core.platform.Logger
import com.mdgroup.nasawallpapers.core.utils.IntentUtils
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme
import com.mdgroup.nasawallpapers.presentation.viewmodels.LicensesViewModel
import com.mdgroup.nasawallpapers.presentation.views.HeaderView
import org.koin.androidx.compose.viewModel
import java.io.IOException

@ExperimentalMaterialApi
@Composable
fun LicensesScreen(navController: NavHostController) {

    val context = LocalContext.current

    val viewModel: LicensesViewModel by viewModel()
    val state = viewModel.state

    try {
        val json = context.assets.open("licenses.json")
            .bufferedReader()
            .use { it.readText() }
        viewModel.fetch(json)
    } catch (exception: IOException) {
        Logger.tag("LicensesScreen").e(exception)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderView(
            label = stringResource(id = R.string.licenses),
            onBack = { navController.popBackStack() }
        )

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            itemsIndexed(state.licenses) { index, item ->
                Card(
                    onClick = { IntentUtils.openUrl(context, item.url) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(
                            top = 16.dp,
                            bottom = if (index == state.licenses.size - 1) 16.dp else 0.dp
                        )
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Text(
                        text = item.title,
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
        LicensesScreen(rememberNavController())
    }
}

@ExperimentalMaterialApi
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        LicensesScreen(rememberNavController())
    }
}