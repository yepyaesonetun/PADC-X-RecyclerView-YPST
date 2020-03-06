package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

class NewsDetailsViewModel : ViewModel() {

    private val mNewsModel: NewsModel = NewsModelImpl

    fun getNewsById(newsId: Int): LiveData<NewsVO> {
        return mNewsModel.getNewsById(newsId)
    }
}