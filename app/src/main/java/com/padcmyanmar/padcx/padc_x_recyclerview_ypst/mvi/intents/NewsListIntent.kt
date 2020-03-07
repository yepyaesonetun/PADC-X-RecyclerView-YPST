package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.intents

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase.MVIIntent

sealed class NewsListIntent : MVIIntent {
    object LoadAllNewsIntent : NewsListIntent()
    object SwipeRefreshNewsIntent : NewsListIntent()
}