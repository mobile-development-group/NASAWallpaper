package com.mdgroup.nasawallpapers.core.di

import com.mdgroup.nasawallpapers.data.network.ApiInterface
import com.mdgroup.nasawallpapers.data.repositories.WallpaperRepositoryImpl
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractor
import com.mdgroup.nasawallpapers.domain.interactors.WallpaperInteractorImpl
import com.mdgroup.nasawallpapers.domain.repositories.WallpaperRepository
import com.mdgroup.nasawallpapers.sqldelight.Database
import org.koin.dsl.module

val wallpaperModule = module {

    single<WallpaperRepository> { WallpaperRepositoryImpl(get<ApiInterface>(), get<Database>()) }

    single<WallpaperInteractor> { WallpaperInteractorImpl(get<WallpaperRepository>()) }
}