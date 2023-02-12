package com.mdgroup.nasawallpapers.presentation.viewmodels

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractor
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import java.util.*

class WallpapersViewModel(private val interactor: NasaInteractor) : BaseViewModel() {

    data class State(
        val isLoading: Boolean = true,
        val wallpapers: MutableList<WallpaperModel>
    )

    var state: State by mutableStateOf(
        State(
            isLoading = true,
            wallpapers = mutableListOf()
        )
    )

    private var lastDate: DateModel = run {
        makeDateModel(Calendar.getInstance())
    }

    init {
        //        for (i in 0..10) {
        fetchWallpapers()
        //        }
    }

    override fun showLoading() {
        state = state.copy(isLoading = true)
    }

    override fun hideLoading() {
        state = state.copy(isLoading = false)
    }

    private fun fetchWallpapers() {
        val count = 10

        showLoading()
        onBackgroundScope {
            for (i in 0..count) {
                val calendar = Calendar.getInstance()
                calendar.set(lastDate.year, lastDate.month - 1, lastDate.day)
                calendar.add(Calendar.DAY_OF_MONTH, i * -1)

                val date = makeDateModel(calendar)
                fetchWallpaper(date)

                if (i == count - 1) {
                    lastDate = date
                }
            }
            hideLoading()
        }
    }

    private suspend fun fetchWallpaper(date: DateModel) {
        val response = interactor.fetch(date)
        if (response.isSuccess) {
            response.data?.let {
                val list = state.wallpapers
                list.add(it)
                state = state.copy(wallpapers = list)
            }
        } else {
            hideLoading()
        }
    }

    private fun makeDateModel(calendar: Calendar): DateModel {
        return DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
    }
}