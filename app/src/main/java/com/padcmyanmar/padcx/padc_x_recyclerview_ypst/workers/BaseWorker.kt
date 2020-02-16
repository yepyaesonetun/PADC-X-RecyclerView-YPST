package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModel
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl

abstract class BaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val mNewsModel : NewsModel = NewsModelImpl
}