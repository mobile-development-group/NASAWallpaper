package com.mdgroup.nasawallpapers.core.di

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import org.koin.dsl.module

private const val CACHE_SIZE_LIMIT = (1500 * 1024 * 1024)

val imageLoaderModule = module {
    single<ImageLoader> { makeImageLoader(get<Context>()) }
}

private fun makeImageLoader(context: Context): ImageLoader =
    ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizeBytes(CACHE_SIZE_LIMIT)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizeBytes(CACHE_SIZE_LIMIT.toLong())
                .build()
        }
        .build()