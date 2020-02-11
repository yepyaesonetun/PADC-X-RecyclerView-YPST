package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import androidx.lifecycle.MutableLiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.DUMMY_ACCESS_TOKEN
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object NewsModelImpl : NewsModel, BaseModel() {

    private val mNewsRepository : HashMap<Int, NewsVO> = hashMapOf()

    override fun getAllNews() : Observable<List<NewsVO>> {


        return mNewsApi
            .getAllNews(DUMMY_ACCESS_TOKEN)
            .map{it.data?.toList() ?: listOf()}
            .doOnNext { it.forEach { news ->
                mNewsRepository[news.id] = news
            }}
            .subscribeOn(Schedulers.io())

        
    }


    override fun getNewsById(newsId : Int) : NewsVO{
        mNewsRepository[newsId]?.let {
            return it
        }
        return NewsVO()
    }
}