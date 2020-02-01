package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

interface NewsModel {
    fun getAllNews()

    fun getNewsById(newsId: Int)
}