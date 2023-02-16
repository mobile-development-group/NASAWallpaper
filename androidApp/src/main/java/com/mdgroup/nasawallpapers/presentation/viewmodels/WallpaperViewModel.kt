package com.mdgroup.nasawallpapers.presentation.viewmodels

import android.app.WallpaperManager
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mdgroup.nasawallpapers.core.platform.Logger
import com.mdgroup.nasawallpapers.core.utils.FileUtils
import com.mdgroup.nasawallpapers.core.utils.IntentUtils
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractor
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import java.time.LocalDate
import java.util.*

class WallpaperViewModel(private val date: String?, private val resources: Resources, private val interactor: WallpaperInteractor) : BaseViewModel() {

    data class State(
        val isLoading: Boolean = true,
        val wallpaper: WallpaperModel?,
        val date: LocalDate
    )

    var state: State by mutableStateOf(
        State(
            isLoading = true,
            wallpaper = null,
            date = LocalDate.now()
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
        val wallpaper = state.wallpaper
        wallpaper?.let {
            wallpaper.uri?.let {
                interactor.delete(wallpaper.dateModel())
            } ?: run {
                showLoading()

                onBackgroundScope {
                    val uri = FileUtils.saveFileUsingMediaStore(context, it.hdurl, it.title)

                    wallpaper.uri = uri.toString()
                    interactor.save(wallpaper)

                    state = state.copy(isLoading = false, wallpaper = wallpaper)
                }
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

    fun random() {
        val calendar = Calendar.getInstance()

        val year = Random().nextInt(calendar.get(Calendar.YEAR) - 1995) + 1995

        val month = if (calendar.get(Calendar.YEAR) == year) {
            Random().nextInt(calendar.get(Calendar.MONTH) + 1) + 1
        } else {
            Random().nextInt(12) + 1
        }

        val day = if (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == month) {
            Random().nextInt(calendar.get(Calendar.DAY_OF_MONTH)) + 1
        } else {
            Random().nextInt(28) + 1
        }

        fetchWallpaper(DateModel(year, month, day))
    }

    fun setAsWallpaper(context: Context, wallpaperManager: WallpaperManager) {
        onBackgroundScope {
            showLoading()
            state.wallpaper?.let { wallpaper ->
                wallpaper.uri?.let {
                    setupWallpaper(context, wallpaperManager, Uri.parse(it))
                    hideLoading()
                } ?: run {
                    FileUtils.saveFileUsingMediaStore(context, wallpaper.hdurl, wallpaper.title)?.let { uri ->
                        setupWallpaper(context, wallpaperManager, uri)
                        hideLoading()
                    }
                }
            }
        }
    }

    fun fetchWallpaper(date: LocalDate) {
        fetchWallpaper(DateModel(date.year, date.monthValue, date.dayOfMonth))
    }

    private fun fetchWallpaper(date: DateModel) {
        onBackgroundScope {
            val response = interactor.fetch(date)
            if (response.isSuccess) {
                response.data?.let {
                    onUiScope {
                        state = State(isLoading = false, wallpaper = it, date = makeLocalDateFromString(it.date))
                    }
                }
            } else {
                hideLoading()
            }
        }
    }

    private fun setupWallpaper(context: Context, wallpaperManager: WallpaperManager, uri: Uri) {
        try {
            val bitmap = FileUtils.bitmapFromUri(context, uri)
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM)
        } catch (exception: Exception) {
            Logger.e(exception)
        }
    }

    private fun makeDateModel(calendar: Calendar): DateModel {
        return DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    }

    private fun makeLocalDateFromString(date: String): LocalDate {
        val data = date.split("-")
        return LocalDate.of(data[0].toInt(), data[1].toInt() + 1, data[2].toInt())
    }
}