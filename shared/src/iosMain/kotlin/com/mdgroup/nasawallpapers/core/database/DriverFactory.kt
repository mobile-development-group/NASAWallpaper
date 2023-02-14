package com.mdgroup.nasawallpapers.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mdgroup.nasawallpapers.sqldelight.Database

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "nasa_wallpaper.db")
    }
}