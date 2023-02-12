package com.mdgroup.nasawallpapers.core.di

import android.content.Context
import android.content.res.Resources
import org.koin.dsl.module

val mainModule = module {
    factory<Resources> { get<Context>().resources }
}