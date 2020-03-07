package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.actions

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase.MVIActions

sealed class NewsListAction : MVIActions{
    object LoadAllNewsAction : NewsListAction()
    object SwipeRefreshNewsAction : NewsListAction()
}