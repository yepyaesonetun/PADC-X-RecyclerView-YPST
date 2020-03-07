package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.WorkerParameters

class GetNewsWorker(context: Context, workerParams: WorkerParameters) :
    BaseWorker(context, workerParams) {

    @SuppressLint("CheckResult")
    override fun doWork(): Result {
        var result = Result.failure()

        mNewsModel.getAllNewsFromApiAndSaveToDatabase()
            .subscribe({
                result = Result.success()
            },{
                result = Result.failure()
            })
        return result
    }
}