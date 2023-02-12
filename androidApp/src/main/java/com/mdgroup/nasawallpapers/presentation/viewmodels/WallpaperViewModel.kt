package com.mdgroup.nasawallpapers.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractor
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import java.util.*

class WallpaperViewModel(private val date: String?, private val interactor: NasaInteractor) : BaseViewModel() {

    data class State(
        val isLoading: Boolean = true,
        val wallpaper: WallpaperModel?
    )

    var state: State by mutableStateOf(
        State(
            isLoading = true,
            wallpaper = null
        )
    )

    override fun showLoading() {
        state = state.copy(isLoading = true)
    }

    override fun hideLoading() {
        state = state.copy(isLoading = false)
    }

    init {
        date?.let {
            val data = it.split("-")
            fetchWallpaper(DateModel(data[0].toInt(), data[1].toInt(), data[2].toInt()))
        } ?: kotlin.run {
            fetchWallpaper(makeDateModel(Calendar.getInstance()))
        }
    }

    private fun fetchWallpaper(date: DateModel) {
        onBackgroundScope {
            val response = interactor.fetch(date)
            if (response.isSuccess) {
                response.data?.let {
                    state = state.copy(isLoading = false, wallpaper = it)
                }
            } else {
                hideLoading()
            }
        }
    }

    private fun makeDateModel(calendar: Calendar): DateModel {
        return DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
    }
}