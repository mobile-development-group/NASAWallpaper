package com.mdgroup.nasawallpapers.core.di

import android.content.res.Resources
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractor
import com.mdgroup.nasawallpapers.presentation.viewmodels.BookmarksViewModel
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpapersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { WallpapersViewModel(get<WallpaperInteractor>()) }

    viewModel { (date: String?) -> WallpaperViewModel(date, get<Resources>(), get<WallpaperInteractor>()) }

    viewModel { BookmarksViewModel(get<WallpaperInteractor>()) }
}