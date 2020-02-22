package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views.NewsDetailsView

class NewsDetailsPresenterImpl : NewsDetailsPresenter, AbstractBasePresenter<NewsDetailsView>() {
    override fun onUiReady(lifeCycleOwner: LifecycleOwner, newsId: Int) {

    }
}