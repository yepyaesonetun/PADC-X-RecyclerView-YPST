package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.daos.NewsDao

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-14.
 */

@Database(entities = [NewsVO::class], version = 7, exportSchema = false)
abstract class NewsDB : RoomDatabase() {
    companion object {
        val DB_NAME = "PADC_NEWS_X.DB"
        var dbInstance: NewsDB? = null

        fun getDBInstance(context: Context): NewsDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, NewsDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun newsDao(): NewsDao
}