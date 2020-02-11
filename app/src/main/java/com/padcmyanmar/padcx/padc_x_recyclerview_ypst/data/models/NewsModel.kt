package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import io.reactivex.Observable

interface NewsModel {
    fun getAllNews() : Observable<List<NewsVO>>

    fun getNewsById(newsId: Int) : NewsVO
}