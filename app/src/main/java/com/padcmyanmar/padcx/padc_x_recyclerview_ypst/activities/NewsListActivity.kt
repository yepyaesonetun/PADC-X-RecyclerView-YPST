package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.NewsApp
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.adapters.NewsListAdapter
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.EM_EMPTY_IMAGE
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.EM_NO_NEWS_AVAILABLE
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewpods.EmptyViewPod
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.NewsListContract
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.interactor.NewsListInterActor
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.presenters.NewsListPresenter
import kotlinx.android.synthetic.main.activity_news_list.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class NewsListActivity : BaseActivity(), NewsListContract.View {

    private lateinit var mAdapter: NewsListAdapter

    private lateinit var viewPodEmpty: EmptyViewPod

    private lateinit var mPresenter: NewsListPresenter

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command?) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                val newsId = command.transitionData as Int
                when (command.screenKey) {
                    NewsDetailActivity.TAG -> startActivity(
                        NewsDetailActivity.newItent(
                            applicationContext,
                            newsId
                        )
                    )
                }
            }
        }
    }

    private val router: Router? by lazy {
        NewsApp.INSTANCE.cicerone.router
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        setUpNavigator()
        setUpPresenter()

        hideEmptyView()
        setUpSwipeRefresh()
        setUpRecyclerView()
        setUpViewPod()

        mPresenter.onUiReady()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onUiDestroyed()
    }

    private fun setUpNavigator() {
        NewsApp.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    private fun setUpPresenter() {
        mPresenter = NewsListPresenter(this, NewsListInterActor(), router)
    }

    private fun setUpViewPod() {
        viewPodEmpty = vpEmpty as EmptyViewPod
        viewPodEmpty.setEmptyData(EM_NO_NEWS_AVAILABLE, EM_EMPTY_IMAGE)
    }

    private fun setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(mPresenter::onSwipeRefresh)
    }

    private fun setUpRecyclerView() {
        mAdapter = NewsListAdapter(mPresenter)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        rvNews.adapter = mAdapter
    }

    override fun showErrorMessage(errorMessage: String) {
        showSnackbar(errorMessage)
    }

    override fun displayNews(newsList: List<NewsVO>) {
        mAdapter.setNewData(newsList.toMutableList())
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showEmptyView() {
        vpEmpty.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        vpEmpty.visibility = View.GONE
    }
}
