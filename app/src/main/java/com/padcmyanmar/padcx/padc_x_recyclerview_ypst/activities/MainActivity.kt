package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.adapters.NewsListAdapter
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewpods.EmptyViewPod
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NewsItemDelegate {


    private lateinit var mNewsModel: NewsModel
    private lateinit var mAdapter: NewsListAdapter

    private lateinit var viewPodEmpty: EmptyViewPod

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNewsModel = NewsModelImpl(this)

        hideEmptyView()
        setUpSwipeRefresh()
        setUpRecyclerView()
        requestData()
        setUpViewPod()
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
            requestData()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = NewsListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        rvNews.adapter = mAdapter
    }

    private fun requestData() {
        swipeRefreshLayout.isRefreshing = true

        mNewsModel.getAllNews(onError = {

            swipeRefreshLayout.isRefreshing = false
            showEmptyView()
            Log.e("error", it)

        }).observe(this, Observer {

            swipeRefreshLayout.isRefreshing = false
            if (it.isNotEmpty()) {
                hideEmptyView()
                mAdapter.setNewData(it.toMutableList())
            }
        })
    }

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
}
