package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.interactor

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.NewsDetailContract

class NewsDetailsInterActor : NewsDetailContract.InterActor {

    private val mNewsModel: NewsModel = NewsModelImpl

    override fun getNewsDetails(newsId: Int): LiveData<NewsVO> {
        return mNewsModel.getNewsById(newsId)
    }
}