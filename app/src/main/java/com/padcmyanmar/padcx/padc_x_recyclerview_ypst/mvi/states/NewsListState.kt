package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.states

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase.MVIState

data class NewsListState(
    val isLoading: Boolean,
    val newsList: List<NewsVO>,
    val error: Throwable?
) : MVIState {
    companion object {
        fun idle(): NewsListState = NewsListState(
            isLoading = false,
            newsList = listOf(),
            error = null
        )
    }
}