package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views.BaseView

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T> {
    var mView: T? = null

    override fun initPresenter(view: T) {
        mView = view
    }
}