package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.DUMMY_ACCESS_TOKEN

object NewsModelImpl : NewsModel, BaseModel() {
    override fun getAllNews(){
        mDataAgent.getAllNews(DUMMY_ACCESS_TOKEN)
    }

    override fun getNewsById(newsId : Int){

    }
}