package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views.BaseView

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T>, ViewModel() {
    var mView: T? = null

    override fun initPresenter(view: T) {
        mView = view
    }
}