package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.intents

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase.MVIIntent

sealed class NewsDetailsIntent : MVIIntent {
    data class LoadNewsDetailsIntent(val newsId : Int) : NewsDetailsIntent()
}