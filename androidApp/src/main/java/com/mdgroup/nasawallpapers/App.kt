package com.mdgroup.nasawallpapers

import android.app.Application
import com.mdgroup.nasawallpapers.core.di.imageLoaderModule
import com.mdgroup.nasawallpapers.core.di.mainModule
import com.mdgroup.nasawallpapers.core.di.viewModelsModule
import com.mdgroup.nasawallpapers.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        initKoin {
            androidContext(this@App)
            modules(
                mainModule,
                imageLoaderModule,
                viewModelsModule
            )
        }
    }
}