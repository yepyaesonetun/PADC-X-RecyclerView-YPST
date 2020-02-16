package com.padcmyanmar.padcx.padc_x_recyclerview_ypst

import android.app.Application
import androidx.work.*
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models.NewsModelImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.workers.GetNewsWorker
import java.util.concurrent.TimeUnit

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        NewsModelImpl.initDatabase(applicationContext)

        getNewsOneTime()
//        getNewsPeriodically()
//        getNewsWhileInDozeMode()
    }

    private fun getNewsOneTime(){
        val getEventsWorkRequest = OneTimeWorkRequest
            .Builder(GetNewsWorker::class.java)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsWorkRequest)
    }

    private fun getNewsPeriodically(){
        val constraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getEventsPeriodicallyWorkRequest = PeriodicWorkRequest
            .Builder(GetNewsWorker::class.java,30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsPeriodicallyWorkRequest)
    }

    private fun getNewsWhileInDozeMode(){
        val constraints = Constraints
            .Builder()
            .setRequiresDeviceIdle(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getEventsWhileInDozeModeWorkRequest = PeriodicWorkRequest
            .Builder(GetNewsWorker::class.java, 1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsWhileInDozeModeWorkRequest)
    }
}