package com.mdgroup.nasawallpapers.core.platform

import platform.UIKit.UIDevice
import kotlin.native.Platform

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val isDebug: Boolean = Platform.isDebugBinary
}