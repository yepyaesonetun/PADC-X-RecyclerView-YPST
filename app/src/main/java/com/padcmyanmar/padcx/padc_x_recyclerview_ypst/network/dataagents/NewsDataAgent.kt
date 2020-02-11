package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.dataagents

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

interface NewsDataAgent {
    fun getAllNews(
        accessToken : String,
        onSuccess: (List<NewsVO>) -> Unit,
        onFailure: (String) -> Unit)
}