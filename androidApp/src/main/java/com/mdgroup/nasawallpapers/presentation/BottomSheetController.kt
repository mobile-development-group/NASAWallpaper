package com.mdgroup.nasawallpapers.presentation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class BottomSheetController(
    private val scope: CoroutineScope,
    private val view: (@Composable BoxScope.() -> Unit) -> Unit,
    val sheetState: ModalBottomSheetState
) {

    fun setView(view: @Composable() (BoxScope.() -> Unit)) {
        this.view.invoke(view)
        show()
    }

    fun show() {
        scope.launch { sheetState.show() }
    }

    fun hide() {
        scope.launch { sheetState.hide() }
    }
}