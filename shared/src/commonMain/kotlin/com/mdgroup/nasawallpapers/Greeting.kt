package com.mdgroup.nasawallpapers

import com.mdgroup.nasawallpapers.core.platform.Platform

class Greeting {
    fun greet(): String {
        return "Hello, ${Platform().platform}!"
    }
}