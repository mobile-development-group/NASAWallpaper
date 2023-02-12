package com.mdgroup.nasawallpapers.core.platform

import com.mdgroup.nasawallpapers.BuildConfig

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val isDebug: Boolean = BuildConfig.DEBUG
}