package com.mdgroup.nasawallpapers.core.di

import com.mdgroup.nasawallpapers.core.platform.Platform
import com.mdgroup.nasawallpapers.data.network.ApiInterface
import com.mdgroup.nasawallpapers.data.network.ApiProvider
import io.ktor.client.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<ApiInterface> {
        val httpClient = provideHttpClient(get<Platform>())
        ApiProvider(httpClient)
    }
}

private fun provideHttpClient(
    platform: Platform
) = HttpClient {

    install(Logging) {
        if (platform.isDebug) {
            this.level = LogLevel.ALL
            this.logger = object : Logger {
                override fun log(message: String) {
                    com.mdgroup.nasawallpapers.core.platform.Logger.tag("HttpClient").print(message)
                }
            }
        }
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        })
    }

    install(HttpCache)
}