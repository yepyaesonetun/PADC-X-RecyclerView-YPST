package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.interactor

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.NewsListContract

class NewsListInterActor : NewsListContract.InterActor {

    private val mNewsModel: NewsModel = NewsModelImpl

    override fun getAllNews(onError: (String) -> Unit): LiveData<List<NewsVO>> {
        return mNewsModel.getAllNews(onError)
    }

    override fun syncNewsListWithServer() {
        mNewsModel.getAllNewsFromApiAndSaveToDatabase({}, {})
    }
}