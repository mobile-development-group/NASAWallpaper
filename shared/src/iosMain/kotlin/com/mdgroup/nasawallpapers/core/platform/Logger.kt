package com.mdgroup.nasawallpapers.core.platform

import platform.darwin.NSObject
import kotlin.native.Platform

/**
🐛 (\uD83D\uDC1B) - Debug
ℹ️ - Info
📖 - Notice
⚠️ - Warning
⚡ - Critical
🔥 (\uD83D\uDD25) - Error
 */
actual object Logger {

    actual var tag: String? = null
        private set

    actual fun tag(tag: String): Logger {
        this.tag = tag
        return this
    }

    actual fun print(msg: String?) {
        if (!Platform.isDebugBinary)
            return

        if (msg == null)
            return

        if (tag == null) {
            println(msg)
        } else {
            println("$tag: $msg")
        }
    }

    actual fun d(msg: String?) {
        print("\uD83D\uDC1B $msg")
    }

    actual fun e(msg: String?) {
        print("\uD83D\uDD25 $msg")
    }

    actual fun e(tr: Throwable?) {
        println(tr)
    }

    actual fun e(msg: String, tr: Throwable?) {
        print(msg)
        println(tr)
    }
}