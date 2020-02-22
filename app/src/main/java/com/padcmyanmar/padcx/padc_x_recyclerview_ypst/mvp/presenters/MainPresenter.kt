package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvp.presenters

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate

interface MainPresenter : NewsItemDelegate {
    fun onUiReady()
}