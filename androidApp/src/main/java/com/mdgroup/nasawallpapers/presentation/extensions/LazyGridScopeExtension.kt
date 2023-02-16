package com.mdgroup.nasawallpapers.presentation.extensions

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T, index: Int) -> Unit
) {
    items(items.itemCount) { index ->
        items[index]?.let { itemContent(it, index) }
    }
}