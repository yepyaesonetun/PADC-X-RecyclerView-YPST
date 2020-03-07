package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.viewmodels

import androidx.lifecycle.ViewModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.actions.NewsListAction
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.intents.NewsListIntent
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase.MVIViewModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.processors.NewsListProcessor
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.results.NewsListResult
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.states.NewsListState
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.notOfType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

class NewsListViewModel() : ViewModel(), MVIViewModel<NewsListState, NewsListIntent> {

    val newsListProcesser: NewsListProcessor = NewsListProcessor

    private val intentsSubject : PublishSubject<NewsListIntent> = PublishSubject.create()
    private val statesObservable : Observable<NewsListState> = compose()

    private val intentFilter : ObservableTransformer<NewsListIntent, NewsListIntent>
    get() = ObservableTransformer { intents ->
        intents.publish{ shared ->
            Observable.merge(
                shared.ofType(NewsListIntent.LoadAllNewsIntent::class.java).take(1),
                shared.notOfType(NewsListIntent.LoadAllNewsIntent::class.java)
            )
        }
    }

    override fun processIntent(intents: Observable<NewsListIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<NewsListState> = statesObservable

    private fun compose() : Observable<NewsListState>{
        return intentsSubject
            .compose(intentFilter)
            .map(this::actionsFromIntents)
            .compose(newsListProcesser.newsProcessor)
            .scan(NewsListState.idle(), reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    private fun actionsFromIntents(intent : NewsListIntent) : NewsListAction{
        return when(intent){
            is NewsListIntent.LoadAllNewsIntent -> NewsListAction.LoadAllNewsAction
            is NewsListIntent.SwipeRefreshNewsIntent -> NewsListAction.SwipeRefreshNewsAction
        }
    }

    companion object {
        private val reducer = BiFunction { previousState: NewsListState, result: NewsListResult ->
            when(result){
                is NewsListResult.LoadAllNewsResult -> when(result){
                    is NewsListResult.LoadAllNewsResult.Success -> {
                        previousState.copy(isLoading = false, newsList = result.news)
                    }
                    is NewsListResult.LoadAllNewsResult.Error -> {
                        previousState.copy(isLoading = false, error = result.error)
                    }
                    is NewsListResult.LoadAllNewsResult.LoadingResult -> {
                        previousState.copy(isLoading = true)
                    }
                }

                is NewsListResult.SwipeRefreshNewsResult -> when(result){
                    is NewsListResult.SwipeRefreshNewsResult.Success -> {
                        previousState.copy(isLoading = false, newsList = result.news)
                    }
                    is NewsListResult.SwipeRefreshNewsResult.Error -> {
                        previousState.copy(isLoading = false, error = result.error)
                    }
                    is NewsListResult.SwipeRefreshNewsResult.LoadingResult -> {
                        previousState.copy(isLoading = true)
                    }
                }
            }
        }
    }
}