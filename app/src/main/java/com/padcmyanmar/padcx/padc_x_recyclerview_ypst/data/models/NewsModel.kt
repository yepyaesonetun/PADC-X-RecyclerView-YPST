package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import io.reactivex.Observable

interface NewsModel {
    fun getAllNews(onError: (String) -> Unit) : LiveData<List<NewsVO>>

    fun getAllNewsFromApiAndSaveToDatabase(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getNewsById(newsId: Int) : LiveData<NewsVO>
}