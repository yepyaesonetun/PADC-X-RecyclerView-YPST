package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mNewsModel = NewsModelImpl

    override fun onTapNewsItem(newsId: Int) {

    }

    override fun onSwipeRefresh() {
        requestAllNews()
    }

    override fun onCreate() {
        requestAllNews()
    }

    private fun requestAllNews() {
        mView?.enableSwipeRefresh()
        mNewsModel.getAllNews(onError = {
            mView?.disableSwipeRefresh()
            mView?.displayEmptyView()
        }).observeForever {
            mView?.disableSwipeRefresh()
            if (it.isEmpty()) mView?.displayEmptyView() else mView?.displayNewsList(it)
        }
    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {
        mView = null
    }

    override fun onDestroy() {
        mView = null
    }
}