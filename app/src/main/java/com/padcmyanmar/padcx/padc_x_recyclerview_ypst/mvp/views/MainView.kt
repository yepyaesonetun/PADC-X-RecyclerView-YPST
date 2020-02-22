package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

interface MainView : BaseView{
    fun displayNewsList(newsList: List<NewsVO>)
    fun navigateToNewsDetails(newsId: Int)
    fun displayEmptyView()
    fun enableSwipeRefresh()
    fun disableSwipeRefresh()
}