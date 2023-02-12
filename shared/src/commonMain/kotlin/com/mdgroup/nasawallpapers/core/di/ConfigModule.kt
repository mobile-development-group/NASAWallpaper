package com.mdgroup.nasawallpapers.core.di

import com.mdgroup.nasawallpapers.core.platform.Platform
import org.koin.dsl.module

val configModule = module {
    single<Platform> { Platform() }
}