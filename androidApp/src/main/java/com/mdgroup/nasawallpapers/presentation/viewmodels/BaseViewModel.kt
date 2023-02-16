package com.mdgroup.nasawallpapers.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.mdgroup.nasawallpapers.core.platform.Logger
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    val main = Dispatchers.Main
    val background = Dispatchers.Default

    private val parentJob by lazy { SupervisorJob() }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exceptionHandler(exception)
    }

    private val scope: CoroutineScope by lazy { CoroutineScope(parentJob + coroutineExceptionHandler) }

    fun onBackgroundScope(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = scope.launch(background, start, block)

    fun onUiScope(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = scope.launch(main, start, block)

    open fun showLoading() {}

    open fun hideLoading() {}

    open fun exceptionHandler(exception: Throwable) {
        Logger.tag("CoroutineExceptionHandler").e(exception)
    }
}