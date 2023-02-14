package com.mdgroup.nasawallpapers.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractor
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel

class BookmarksViewModel(private val interactor: WallpaperInteractor) : BaseViewModel() {

    data class State(
        val isLoading: Boolean = true,
        val bookmarks: List<WallpaperModel>
    )

    var state: State by mutableStateOf(
        State(
            isLoading = true,
            bookmarks = interactor.getAll()
        )
    )
}