package com.mdgroup.nasawallpapers.core.platform

expect class Platform() {
    val platform: String
    val isDebug: Boolean
}