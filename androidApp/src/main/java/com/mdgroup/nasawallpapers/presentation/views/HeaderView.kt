package com.mdgroup.nasawallpapers.presentation.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.ApplicationTheme

@Composable
fun HeaderView(
    label: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    onBack: (() -> Unit)? = null
) {
    Box(modifier = modifier) {
        if (onBack != null) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_content_description),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        }
        Text(
            text = label,
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp),
            color = MaterialTheme.colors.onBackground
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
    }
}

/** Previews */
@Preview(name = "Light mode", showBackground = true)
@Composable
private fun LightPreview() {
    ApplicationTheme {
        HeaderView(
            label = "Dashboard",
            actions = {
                IconButton(onClick = {

                }) {
                    Icon(
                        painterResource(id = R.drawable.ic_gear),
                        stringResource(R.string.settings),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
                IconButton(onClick = {

                }) {
                    Icon(
                        painterResource(id = R.drawable.ic_calendar),
                        stringResource(R.string.save_content_description),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
            },
            onBack = { }
        )
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    ApplicationTheme {
        HeaderView(
            label = "Dashboard",
            onBack = { }
        )
    }
}