package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.processors

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.actions.NewsListAction
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.actions.NewsListAction.*
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.results.NewsListResult
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.results.NewsListResult.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException

object NewsListProcessor{

    private val mNewsModel: NewsModel = NewsModelImpl

    private val loadAllNewsProcessor =
        ObservableTransformer<LoadAllNewsAction, LoadAllNewsResult>{ actions ->
            actions.flatMap {
                mNewsModel.getAllNews()
                    .map { LoadAllNewsResult.Success(it) }
                    .cast(LoadAllNewsResult::class.java)
                    .onErrorReturn{ LoadAllNewsResult.Error(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(LoadAllNewsResult.LoadingResult)
            }
        }

    private val swipeRefreshNewsProcessor =
        ObservableTransformer<SwipeRefreshNewsAction, SwipeRefreshNewsResult> { actions ->
            actions.flatMap {
                mNewsModel.getAllNewsFromApiAndSaveToDatabase()
                    .map { SwipeRefreshNewsResult.Success(it) }
                    .cast(SwipeRefreshNewsResult::class.java)
                    .onErrorReturn{ SwipeRefreshNewsResult.Error(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(SwipeRefreshNewsResult.LoadingResult)
            }
        }

    internal var newsProcessor =
        ObservableTransformer<NewsListAction, NewsListResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(LoadAllNewsAction::class.java).compose(loadAllNewsProcessor),
                    shared.ofType(SwipeRefreshNewsAction::class.java).compose(swipeRefreshNewsProcessor)
                ).mergeWith(
                    shared.filter{
                        it !is LoadAllNewsAction
                                && it !is SwipeRefreshNewsAction
                    }.flatMap {
                        Observable.error<NewsListResult>(IllegalArgumentException("No Action Listed"))
                    }
                )
            }
        }
}