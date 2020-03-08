package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.viper

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

interface NewsDetailContract {
    interface View {
        fun displayNewsDetails(news: NewsVO)
    }

    interface Presenter {
        fun onUiReady(newsId : Int)
        fun onUiDestroyed()
    }

    interface InterActor {
        fun getNewsDetails(newsId: Int): LiveData<NewsVO>
    }

    interface InterActorOutput {
        fun onNewsDetailsFetchSuccess(news: NewsVO)
    }
}