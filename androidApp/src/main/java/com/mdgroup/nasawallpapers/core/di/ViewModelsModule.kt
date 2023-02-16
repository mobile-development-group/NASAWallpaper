package com.mdgroup.nasawallpapers.core.di

import android.content.res.Resources
import com.mdgroup.nasawallpapers.domain.interactors.LicenseInteractor
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractor
import com.mdgroup.nasawallpapers.presentation.viewmodels.BookmarksViewModel
import com.mdgroup.nasawallpapers.presentation.viewmodels.LicensesViewModel
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpapersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { WallpapersViewModel(interactor = get<WallpaperInteractor>()) }

    viewModel { (date: String?) -> WallpaperViewModel(date = date, resources = get<Resources>(), interactor = get<WallpaperInteractor>()) }

    viewModel { BookmarksViewModel(interactor = get<WallpaperInteractor>()) }

    viewModel { LicensesViewModel(interactor = get<LicenseInteractor>()) }
}