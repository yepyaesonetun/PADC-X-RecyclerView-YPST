package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.CommentVO

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-14.
 */

class CommentListTypeConverter {
    @TypeConverter
    fun toString(commentList: ArrayList<CommentVO>): String {
        return Gson().toJson(commentList)
    }

    @TypeConverter
    fun toCommentList(commentListJsonStr: String): ArrayList<CommentVO> {
        val commentListType = object : TypeToken<ArrayList<CommentVO>>() {}.type
        return Gson().fromJson(commentListJsonStr, commentListType)
    }
}