package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate

interface NewsListContract {
    interface View {
        fun displayNews(newsList: List<NewsVO>)
        fun showLoading()
        fun hideLoading()
        fun showEmptyView()
        fun hideEmptyView()
        fun showErrorMessage(errorMessage: String)
    }

    interface Presenter : NewsItemDelegate {
        fun onUiReady()
        fun onUiDestroyed()
        fun onSwipeRefresh()
    }

    interface InterActor {
        fun getAllNews(onError: (String) -> Unit): LiveData<List<NewsVO>>
        fun syncNewsListWithServer()
    }

    interface InterActorOutput {
        fun onNewsListFetchSuccess(newsList: List<NewsVO>)
        fun onNewsListFetchFailure(errorMessage: String)
        fun onNewsListEmpty()
    }
}