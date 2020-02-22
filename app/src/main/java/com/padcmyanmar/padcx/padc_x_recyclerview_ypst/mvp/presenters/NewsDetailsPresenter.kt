package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views.NewsDetailsView

interface NewsDetailsPresenter : BasePresenter<NewsDetailsView>{
    fun onUiReady(lifeCycleOwner: LifecycleOwner, newsId : Int)
}