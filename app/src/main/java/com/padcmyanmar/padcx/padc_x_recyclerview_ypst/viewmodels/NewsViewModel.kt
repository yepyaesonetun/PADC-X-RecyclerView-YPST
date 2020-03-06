package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate

class NewsViewModel : ViewModel(), NewsItemDelegate {


    private val mNewsModel: NewsModel = NewsModelImpl

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    private val newsListLiveData: LiveData<List<NewsVO>> = mNewsModel.getAllNews {
        errorLiveData.postValue(it)
    }

    private val navigateToNewsDetailsLiveData: MutableLiveData<Int> = MutableLiveData()

    private val enableSwipeRefreshLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val disableSwipeRefreshLiveData: MutableLiveData<Unit> = MutableLiveData()

    fun getNewsListLiveData(): LiveData<List<NewsVO>> {
        return newsListLiveData
    }

    fun getErrorLiveData(): LiveData<String> {
        return errorLiveData
    }

    fun getNavigateToNewsDetailsLiveData(): LiveData<Int> {
        return navigateToNewsDetailsLiveData
    }

    fun getEnableSwipeRefreshLiveData() : LiveData<Unit>{
        return enableSwipeRefreshLiveData
    }

    fun getDisableSwipeRefreshLiveData() : LiveData<Unit>{
        return disableSwipeRefreshLiveData
    }

    fun onSwipeRefresh() {
        enableSwipeRefreshLiveData.postValue(Unit)
        mNewsModel.getAllNewsFromApiAndSaveToDatabase(
            onSuccess = {
                disableSwipeRefreshLiveData.postValue(Unit)
            }, onError = {
                disableSwipeRefreshLiveData.postValue(Unit)
                errorLiveData.postValue(it)
            }
        )
    }

    override fun onTapNewsItem(newsId: Int) {
        navigateToNewsDetailsLiveData.postValue(newsId)
    }
}