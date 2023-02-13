package com.mdgroup.nasawallpapers.core.di

import android.content.res.Resources
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractor
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpaperViewModel
import com.mdgroup.nasawallpapers.presentation.viewmodels.WallpapersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { WallpapersViewModel(get<NasaInteractor>()) }

    viewModel { (date: String?) -> WallpaperViewModel(date, get<Resources>(), get<NasaInteractor>()) }
}