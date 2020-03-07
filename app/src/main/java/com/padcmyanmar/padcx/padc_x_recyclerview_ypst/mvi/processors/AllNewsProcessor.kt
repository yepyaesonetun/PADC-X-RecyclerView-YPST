package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.processors

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.actions.NewsListAction.*
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.results.NewsListResult
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.results.NewsListResult.*
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllNewsProcessor(
    val mNewsModel: NewsModel = NewsModelImpl
) {
    val loadAllNewsProcessor =
        ObservableTransformer<LoadAllNewsAction, LoadAllNewsResult>{ actions ->
            actions.flatMap {
                mNewsModel.getAllNews()
                    .map { LoadAllNewsResult.Success(it) }
                    .cast(LoadAllNewsResult::class.java)
                    .onErrorReturn{ LoadAllNewsResult.Error(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
}