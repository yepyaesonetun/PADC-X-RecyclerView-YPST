package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-14.
 */

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsVO>>

    @Query("SELECT * FROM news WHERE id = :noteId")
    fun getNewsById(noteId: Int) : LiveData<NewsVO>

    @Query("DELETE FROM news")
    fun deleteAll()

    @Delete
    fun deleteNews(note: NewsVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(noteVO: NewsVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(news: List<NewsVO>)
}