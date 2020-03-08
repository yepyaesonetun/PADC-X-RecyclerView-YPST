package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.presenters

import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities.NewsDetailActivity
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.NewsDetailContract
import ru.terrakok.cicerone.Router

class NewsDetailPresenter(
    var mView: NewsDetailContract.View?,
    var mInterActor: NewsDetailContract.InterActor?,
    var mRouter: Router?
) : NewsDetailContract.Presenter, NewsDetailContract.InterActorOutput {

    override fun onUiReady(newsId: Int) {
        mInterActor?.getNewsDetails(newsId)
            ?.observe(mView as NewsDetailActivity, Observer {
                onNewsDetailsFetchSuccess(it)
            })
    }

    override fun onUiDestroyed() {
        mView = null
        mInterActor = null
    }

    override fun onNewsDetailsFetchSuccess(news: NewsVO) {
        mView?.displayNewsDetails(news)
    }

}