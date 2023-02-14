package com.mdgroup.nasawallpapers.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mdgroup.nasawallpapers.domain.WallpapersPagingSource
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractor

class WallpapersViewModel(private val interactor: WallpaperInteractor) : BaseViewModel() {

    val wallpapers = Pager(PagingConfig(pageSize = 1, prefetchDistance = 4)) {
        WallpapersPagingSource(interactor)
    }.flow.cachedIn(viewModelScope)

}