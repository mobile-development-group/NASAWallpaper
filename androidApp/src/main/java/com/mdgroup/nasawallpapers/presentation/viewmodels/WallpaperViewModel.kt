package com.mdgroup.nasawallpapers.presentation.viewmodels

import android.app.WallpaperManager
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mdgroup.nasawallpapers.core.utils.FileUtils
import com.mdgroup.nasawallpapers.core.utils.IntentUtils
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractor
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import java.util.*

class WallpaperViewModel(private val date: String?, private val resources: Resources, private val interactor: NasaInteractor) : BaseViewModel() {

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

    fun save(context: Context) {
        state.wallpaper?.let {
            showLoading()

            onBackgroundScope {
                val uri = FileUtils.saveFileUsingMediaStore(context, it.hdurl, it.title)
                state = state.copy(isLoading = false, wallpaper = it.copy(uri = uri.toString()))
                //TODO тут сохранить в БД
            }
        }
    }

    fun share(context: Context) {
        state.wallpaper?.let { wallpaper ->
            wallpaper.uri?.let {
                IntentUtils.sendPhotos(context, Uri.parse(it))
            } ?: run {
                showLoading()
                onBackgroundScope {
                    FileUtils.saveFileUsingMediaStore(context, wallpaper.hdurl, wallpaper.title)?.let { uri ->
                        IntentUtils.sendPhotos(context, uri)
                    }
                    hideLoading()
                }
            }
        }
    }

    fun setAsWallpaper(context: Context, wallpaperManager: WallpaperManager) {
        state.wallpaper?.let { wallpaper ->
            wallpaper.uri?.let {
                setupWallpaper(context, wallpaperManager, Uri.parse(it))
            } ?: run {
                showLoading()
                onBackgroundScope {
                    FileUtils.saveFileUsingMediaStore(context, wallpaper.hdurl, wallpaper.title)?.let { uri ->
                        setupWallpaper(context, wallpaperManager, uri)
                    }
                    hideLoading()
                }
            }
        }
    }

    private fun setupWallpaper(context: Context, wallpaperManager: WallpaperManager, uri: Uri) {
        val bitmap = FileUtils.createBitmapFromUri(context, uri)
        wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM)
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