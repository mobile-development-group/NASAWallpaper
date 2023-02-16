package com.mdgroup.nasawallpapers.core.di

import com.mdgroup.nasawallpapers.core.platform.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()

    // https://github.com/InsertKoinIO/koin/issues/997#event-4434783984
    modules(
        configModule,
        platformModule,
        networkModule,

        // Data/Domain modules
        wallpaperModule,
        licenseModule
    )
}

fun initKoin() = initKoin {}