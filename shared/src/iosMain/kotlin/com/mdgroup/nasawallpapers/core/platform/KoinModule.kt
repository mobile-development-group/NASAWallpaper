package com.mdgroup.nasawallpapers.core.platform

import com.mdgroup.nasawallpapers.core.database.DriverFactory
import com.mdgroup.nasawallpapers.sqldelight.Database
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<Database> { Database(createDriver()) }
}

fun createDriver() = DriverFactory().createDriver()