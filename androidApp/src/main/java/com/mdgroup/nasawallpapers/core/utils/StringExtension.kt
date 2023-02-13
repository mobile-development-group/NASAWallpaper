package com.mdgroup.nasawallpapers.core.utils

fun String.nullIfEmpty(): String? {
    return ifEmpty { null }
}