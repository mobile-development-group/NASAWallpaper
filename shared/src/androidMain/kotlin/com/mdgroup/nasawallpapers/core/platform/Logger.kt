package com.mdgroup.nasawallpapers.core.platform

import android.util.Log
import com.mdgroup.nasawallpapers.BuildConfig

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
        println(Log.DEBUG, msg)
    }

    actual fun d(msg: String?) {
        val stackTrace = Thread.currentThread().stackTrace[3]
        println(Log.DEBUG, "\uD83D\uDC1B ${makeTitle((stackTrace))}: $msg")
    }

    actual fun e(msg: String?) {
        if (!BuildConfig.DEBUG)
            return

        val stackTrace = Thread.currentThread().stackTrace[3]
        Log.e(tag, "\uD83D\uDD25 ${makeTitle((stackTrace))}: $msg", null)
    }

    actual fun e(tr: Throwable?) {
        if (!BuildConfig.DEBUG)
            return

        val stackTrace = Thread.currentThread().stackTrace[3]
        Log.e(tag, "\uD83D\uDD25 ${makeTitle((stackTrace))}", tr)
    }

    actual fun e(msg: String, tr: Throwable?) {
        if (!BuildConfig.DEBUG)
            return

        val stackTrace = Thread.currentThread().stackTrace[3]
        Log.e(tag, "\uD83D\uDD25 ${makeTitle((stackTrace))}: $msg", tr)
    }

    private fun makeTitle(element: StackTraceElement): String {
        return "${element.fileName}.${element.methodName}(${element.fileName}:${element.lineNumber})"
    }

    private fun println(level: Int, msg: String?) {
        if (!BuildConfig.DEBUG)
            return

        if (msg == null)
            return

        Log.println(level, tag, msg)
    }
}