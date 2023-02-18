package com.mdgroup.nasawallpapers.presentation.screens.calendar

import android.app.WallpaperManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.presentation.screens.wallpapers.WallpaperDetailsView
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import com.squaredem.composecalendar.ComposeCalendar
import com.squaredem.composecalendar.model.CalendarColors
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate

@Composable
fun CalendarScreen() {

    val context = LocalContext.current

    val viewModel: WallpaperViewModel by viewModel {
        parametersOf(null)
    }
    val state = viewModel.state

    val isShowDatePicker = rememberSaveable { mutableStateOf(false) }

    Box {
        state.wallpaper?.let { wallpaper ->
            WallpaperDetailsView(
                context = context,
                wallpaper = wallpaper,
                onClickSave = { viewModel.save(context) },
                onClickShare = { viewModel.share(context) },
                onClickAsWallpaper = {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    viewModel.setAsWallpaper(context, wallpaperManager)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { viewModel.random() }) {
                Icon(
                    painterResource(id = R.drawable.ic_questionmark),
                    contentDescription = stringResource(id = R.string.back_content_description),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
            IconButton(onClick = {
                isShowDatePicker.value = true
            }) {
                Icon(
                    painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(id = R.string.back_content_description),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }

    if (isShowDatePicker.value) {
        val minDate = LocalDate.of(1995, 6, 16)
        val now = LocalDate.now()

        ComposeCalendar(
            calendarColors = CalendarColors(
                containerColor = MaterialTheme.colors.background,
                monthChevron = MaterialTheme.colors.primaryVariant,
                defaultText = MaterialTheme.colors.onPrimary,
                disabledText = MaterialTheme.colors.secondaryVariant,
                selectedDayText = MaterialTheme.colors.secondary,
                selectedDayBackground = MaterialTheme.colors.primaryVariant,
                currentDayHighlight = MaterialTheme.colors.primaryVariant,
                inRangeDayBackground = MaterialTheme.colors.primary.copy(alpha = 0.2f),
                inRangeDayText = MaterialTheme.colors.onPrimary,
                todayButtonText = MaterialTheme.colors.primary,
                yearPickerTitleHighlight = MaterialTheme.colors.primaryVariant,
                yearPickerText = MaterialTheme.colors.onPrimary,
                yearPickerSelectedText = MaterialTheme.colors.secondary,
                yearPickerSelectedBackground = MaterialTheme.colors.primaryVariant,
                yearPickerCurrentYearHighlight = MaterialTheme.colors.primaryVariant,
                dayOfWeek = MaterialTheme.colors.primaryVariant,
                headerText = MaterialTheme.colors.primaryVariant,
                dividerColor = MaterialTheme.colors.primary.copy(alpha = 0.25f),
            ),
            startDate = state.date,
            minDate = minDate,
            maxDate = LocalDate.now(),
            onDone = {
                if (minDate < it && it < now) {
                    viewModel.fetch(it)
                }
                isShowDatePicker.value = false
            },
            onDismiss = {
                isShowDatePicker.value = false
            }
        )
    }
}