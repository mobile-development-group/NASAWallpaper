package com.mdgroup.nasawallpapers.core.platform

expect object Logger {

    var tag : String?
        private set

    fun tag(tag: String) : Logger

    fun print(msg: String?)

    fun d(msg: String?)

    fun e(msg: String?)

    fun e(tr: Throwable?)

    fun e(msg: String, tr: Throwable?)
}