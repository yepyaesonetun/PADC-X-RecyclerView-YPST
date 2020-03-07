package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.results

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

sealed class NewsListResult {
    sealed class LoadAllNewsResult : NewsListResult(){
        object LoadingResult : LoadAllNewsResult()
        data class Success(val news : List<NewsVO>) : LoadAllNewsResult()
        data class Error(val error : Throwable) : LoadAllNewsResult()
    }

    sealed class SwipeRefreshNewsResult : NewsListResult(){
        object LoadingResult : SwipeRefreshNewsResult()
        data class Success(val news : List<NewsVO>) : SwipeRefreshNewsResult()
        data class Error(val error : Throwable) : SwipeRefreshNewsResult()
    }
}