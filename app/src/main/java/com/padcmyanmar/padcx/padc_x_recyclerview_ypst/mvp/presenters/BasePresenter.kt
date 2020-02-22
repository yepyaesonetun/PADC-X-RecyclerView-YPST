package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.views.BaseView

interface BasePresenter<T : BaseView> {

    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun initPresenter(view: T)
}