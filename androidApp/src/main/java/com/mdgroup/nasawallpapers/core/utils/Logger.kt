package com.mdgroup.nasawallpapers.core.utils

import android.util.Log
import com.mdgroup.nasawallpapers.BuildConfig

object Logger {

    private var tree: Tree = DebugTree()

    fun setTree(tree: Tree) {
        Logger.tree = tree
    }

    fun print(msg: String) {
        println(Log.DEBUG, msg)
    }

    fun d(msg: String) {
        val stackTrace = Thread.currentThread().stackTrace[3]
        println(Log.DEBUG, "\uD83D\uDC1B ${makeTitle((stackTrace))}: $msg")
    }

    fun e(msg: String) {
        if (!BuildConfig.DEBUG)
            return

        val stackTrace = Thread.currentThread().stackTrace[3]
        Log.e(null, "\uD83D\uDD25 ${makeTitle((stackTrace))}: $msg", null)
    }

    fun e(tr: Throwable?) {
        if (!BuildConfig.DEBUG)
            return

        val stackTrace = Thread.currentThread().stackTrace[3]
        Log.e(null, "\uD83D\uDD25 ${makeTitle((stackTrace))}", tr)
    }

    fun e(msg: String, tr: Throwable?) {
        if (!BuildConfig.DEBUG)
            return

        val stackTrace = Thread.currentThread().stackTrace[3]
        Log.e(null, "\uD83D\uDD25 ${makeTitle((stackTrace))}: $msg", tr)
    }

    private fun makeTitle(element: StackTraceElement): String {
        return "${element.fileName}.${element.methodName}(${element.fileName}:${element.lineNumber})"
    }

    private fun println(level: Int, msg: String) {
        tree.print(level, null, msg)
    }

    interface Tree {

        fun print(level: Int, tag: String?, msg: String)
    }

    class DebugTree : Tree {

        override fun print(level: Int, tag: String?, msg: String) {
            if (!BuildConfig.DEBUG)
                return
            Log.println(level, tag, msg)
        }
    }
}