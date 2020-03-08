package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.NewsApp
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.NewsDetailContract
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.interactor.NewsDetailsInterActor
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper.presenters.NewsDetailPresenter
import kotlinx.android.synthetic.main.activity_news_detail.*
import ru.terrakok.cicerone.Router

class NewsDetailActivity : BaseActivity(), NewsDetailContract.View {

    companion object {

        const val TAG = "NewsDetailsActivity"

        private const val NEWS_ID_EXTRA = "News Id Extra"

        fun newIntent(context: Context, newsId: Int): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(NEWS_ID_EXTRA, newsId)
            return intent
        }
    }

    private lateinit var mPresenter: NewsDetailPresenter

    private val router: Router? by lazy {
        NewsApp.INSTANCE.cicerone.router
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val newsId = intent.getIntExtra(NEWS_ID_EXTRA, 0)
        setUpPresenter()
        mPresenter.onUiReady(newsId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onUiDestroyed()
    }

    private fun setUpPresenter() {
        mPresenter = NewsDetailPresenter(this, NewsDetailsInterActor(), router)
    }

    override fun displayNewsDetails(news: NewsVO) {
        bindData(news)
    }

    private fun bindData(news: NewsVO) {
        tvNewsHeadLine.text = news.title

        Glide.with(this)
            .load(news.heroImage)
            .into(ivNewsImage)

        tvNewsDescription.text = news.description

        news.comments.forEach {
            Log.e("ccc", it.commentMessage)
        }
    }
}
