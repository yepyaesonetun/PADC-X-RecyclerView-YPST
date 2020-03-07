package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.adapters.NewsListAdapter
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.intents.NewsListIntent
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase.MVIView
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.states.NewsListState
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.viewmodels.NewsListViewModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.EM_NO_INTERNET_CONNECTION
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewpods.EmptyViewPod
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NewsItemDelegate
    , MVIView<NewsListIntent, NewsListState> {


    private lateinit var mAdapter: NewsListAdapter

    private lateinit var viewPodEmpty: EmptyViewPod

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val swipeRefreshPublisher =
        PublishSubject.create<NewsListIntent.SwipeRefreshNewsIntent>()

    private lateinit var mViewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()

        hideEmptyView()
        setUpSwipeRefresh()
        setUpRecyclerView()
        //requestData()
        setUpViewPod()
    }

    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    private fun bind() {
        mViewModel
            .states()
            .subscribe(this::render)
            .addTo(compositeDisposable)

        mViewModel.processIntent(intents())
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
    }

    private fun setUpViewPod() {
        viewPodEmpty = vpEmpty as EmptyViewPod
        viewPodEmpty.setEmptyData(
            "There are no news available",
            "https://point-broadband.com/wp-content/uploads/2017/06/No-data-caps-graphic-e1497904686711.png"
        )
    }

    private fun setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            //requestData()
            swipeRefreshPublisher.onNext(NewsListIntent.SwipeRefreshNewsIntent)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = NewsListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        rvNews.adapter = mAdapter
    }

//    private fun requestData() {
//        swipeRefreshLayout.isRefreshing = true
//
//        mNewsModel.getAllNews(onError = {
//
//            swipeRefreshLayout.isRefreshing = false
//            showEmptyView()
//            Log.e("error", it)
//
//        }).observe(this, Observer {
//
//            swipeRefreshLayout.isRefreshing = false
//            if (it.isNotEmpty()) {
//                hideEmptyView()
//                mAdapter.setNewData(it.toMutableList())
//            }
//        })
//    }

    private fun showEmptyView() {
        vpEmpty.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        vpEmpty.visibility = View.GONE
    }


    override fun onTapNewsItem(newsId: Int) {
        startActivity(NewsDetailActivity.newItent(this, newsId))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun render(states: NewsListState) {
        swipeRefreshLayout.isRefreshing = states.isLoading

        if(states.newsList.isEmpty()){
            rvNews.visibility = View.GONE
            showEmptyView()
        } else {
            rvNews.visibility = View.VISIBLE
            hideEmptyView()
            mAdapter.setNewData(states.newsList.toMutableList())
        }

        states.error?.let {
            showSnackbar(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
        }
    }

    override fun intents(): Observable<NewsListIntent> {
        return Observable.merge(
            loadIntent(),
            swipeRefreshIntent()
        )
    }

    private fun loadIntent(): Observable<NewsListIntent.LoadAllNewsIntent> {
        return Observable.just(NewsListIntent.LoadAllNewsIntent)
    }

    private fun swipeRefreshIntent(): Observable<NewsListIntent.SwipeRefreshNewsIntent> {
        return swipeRefreshPublisher
    }

    companion object {
        const val TAG = "NewsListActivity"
    }
}
