package com.mdgroup.nasawallpapers.presentation.screens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.core.utils.IntentUtils
import com.mdgroup.nasawallpapers.presentation.BottomSheetController
import com.mdgroup.nasawallpapers.presentation.navigation.Router
import com.mdgroup.nasawallpapers.presentation.sheets.RatingView
import com.mdgroup.nasawallpapers.presentation.views.HeaderView
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(navController: NavHostController, bottomSheetController: BottomSheetController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 56.dp),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                HeaderView(stringResource(id = R.string.settings))
            }

            item {
                val developerEmail = stringResource(id = R.string.developer_email)
                OtherSector(
                    onClickWriteToDeveloper = { IntentUtils.sendEmailToDeveloper(context, developerEmail) },
                    onClickRateApp = {
                        bottomSheetController.setView {
                            val rateThanksText = stringResource(id = R.string.rate_thanks)
                            RatingView { rating ->
                                if (rating >= 4) {
                                    IntentUtils.openMarket(context, context.packageName)
                                } else {
                                    Toast.makeText(context, rateThanksText, Toast.LENGTH_SHORT).show()
                                }
                                scope.launch { bottomSheetController.hide() }
                            }
                        }
                    },
                    onClickAboutApp = { navController.navigate(Router.ABOUT_APP) },
                    onClickMoreApps = { navController.navigate(Router.MORE_APPS) }
                )
            }
        }
    }
}