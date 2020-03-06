package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.adapters.NewsListAdapter
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewpods.EmptyViewPod
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viewmodels.NewsViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    //private val mNewsModel: NewsModel = NewsModelImpl
    private lateinit var mAdapter: NewsListAdapter

    private lateinit var viewPodEmpty: EmptyViewPod

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var mViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()

        hideEmptyView()
        setUpSwipeRefresh()
        setUpRecyclerView()
        setUpViewPod()
        setUpDataObservations()
    }

    private fun setUpDataObservations(){
        mViewModel.getNewsListLiveData().observe(this, Observer {
            if(it.isEmpty()){
                showEmptyView()
            } else {
                mAdapter.setNewData(it.toMutableList())
            }
        })

        mViewModel.getErrorLiveData().observe(this, Observer {
            showSnackbar(it)
        })

        mViewModel.getNavigateToNewsDetailsLiveData().observe(this, Observer {
            startActivity(NewsDetailActivity.newItent(this, it))
        })

        mViewModel.getEnableSwipeRefreshLiveData().observe(this, Observer {
            swipeRefreshLayout.isRefreshing = true
        })

        mViewModel.getDisableSwipeRefreshLiveData().observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
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
            mViewModel.onSwipeRefresh()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = NewsListAdapter(mViewModel)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        rvNews.adapter = mAdapter
    }

    private fun showEmptyView() {
        vpEmpty.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        vpEmpty.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
