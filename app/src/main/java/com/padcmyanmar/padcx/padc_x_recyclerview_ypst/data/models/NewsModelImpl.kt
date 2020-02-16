package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.db.NewsDB
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.DUMMY_ACCESS_TOKEN
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.EM_NO_INTERNET_CONNECTION
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object NewsModelImpl : NewsModel, BaseModel() {

    override fun getAllNews(onError: (String) -> Unit): LiveData<List<NewsVO>> {
        return mTheDB.newsDao()
            .getAllNews()
    }

    @SuppressLint("CheckResult")
    override fun getAllNewsFromApiAndSaveToDatabase(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mNewsApi
            .getAllNews(DUMMY_ACCESS_TOKEN)
            .map { it.data?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                mTheDB.newsDao().insertAllNews(it)
            },{
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })
    }


    override fun getNewsById(newsId: Int): LiveData<NewsVO> {

        return mTheDB.newsDao().getNewsById(newsId)
    }
}