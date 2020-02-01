package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.adapters.NewsListAdapter
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.DummyNewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NewsItemDelegate {


    private val mNewsModel : NewsModel = NewsModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //showAlert("Title", "Message")

        val adapter = NewsListAdapter(this)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val gridLayoutManager = GridLayoutManager(this, 3)

        rvNews.layoutManager = linearLayoutManager

        rvNews.adapter = adapter

        showSnackbar("This is News List")

        mNewsModel.getAllNews()

    }


    override fun onTapNewsItem() {
        startActivity(NewsDetailActivity.newItent(this))
    }

}
