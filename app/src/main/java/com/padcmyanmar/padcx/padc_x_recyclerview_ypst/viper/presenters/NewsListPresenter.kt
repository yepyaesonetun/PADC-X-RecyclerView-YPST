package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.presenters

import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities.NewsDetailActivity
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities.NewsListActivity
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.NewsListContract
import ru.terrakok.cicerone.Router

class NewsListPresenter(
    private var mView: NewsListContract.View?,
    private var mInterActor: NewsListContract.InterActor?,
    private var mRouter : Router?
) : NewsListContract.Presenter, NewsListContract.InterActorOutput {

    override fun onUiReady() {
        mView?.showLoading()
        mInterActor?.getAllNews(onError = {
            onNewsListFetchFailure(it)
        })?.observe(mView as NewsListActivity, Observer {
            onNewsListFetchSuccess(it)
        })
    }

    override fun onUiDestroyed() {
        mView = null
        mInterActor = null
    }

    override fun onSwipeRefresh() {
        mInterActor?.syncNewsListWithServer()
    }

    override fun onTapNewsItem(newsId: Int) {
        mRouter?.navigateTo(NewsDetailActivity.TAG, newsId)
    }

    override fun onNewsListFetchSuccess(newsList: List<NewsVO>) {
        mView?.hideLoading()
        mView?.hideEmptyView()
        mView?.displayNews(newsList)
    }

    override fun onNewsListFetchFailure(errorMessage: String) {
        mView?.hideLoading()
        mView?.showErrorMessage(errorMessage)
    }

    override fun onNewsListEmpty() {
        mView?.hideLoading()
        mView?.showEmptyView()
    }
}