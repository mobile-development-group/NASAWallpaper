package com.mdgroup.nasawallpapers.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mdgroup.nasawallpapers.core.platform.Logger
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractor
import com.mdgroup.nasawallpapers.domain.models.DateModel
import com.mdgroup.nasawallpapers.domain.models.WallpaperModel
import java.util.*

class WallpapersPagingSource(private val interactor: NasaInteractor) : PagingSource<Int, WallpaperModel>() {

    private val calendar = Calendar.getInstance()

    override fun getRefreshKey(state: PagingState<Int, WallpaperModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallpaperModel> {
        return try {
            val page = params.key ?: 0
            if (page != 0) {
                calendar.add(Calendar.DATE, -1)
            }
            val endDate = makeDateModel(calendar)
            calendar.add(Calendar.DATE, -1 * params.loadSize)
            val startDate = makeDateModel(calendar)

            val response = interactor.fetch(startDate, endDate)
            if (response.isSuccess) {
                response.data?.let {
                    return LoadResult.Page(
                        data = it,
                        prevKey = if (page == 0) null else page.minus(1),
                        nextKey = page.plus(1),
                    )
                }
            }

            LoadResult.Error(Throwable(response.ex))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun makeDateModel(calendar: Calendar): DateModel {
        return DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
    }
}