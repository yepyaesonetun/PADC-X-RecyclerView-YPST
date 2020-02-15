package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.PublicationVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.UserVO

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-14.
 */

class LikeUsersTypeConverter {
    @TypeConverter
    fun toString(userList: ArrayList<UserVO>) : String {
        return Gson().toJson(userList)
    }

    @TypeConverter
    fun toUserList(userListJsonString: String) : ArrayList<UserVO>{
        val usetListType = object : TypeToken<ArrayList<UserVO>>() {}.type
        return Gson().fromJson(userListJsonString, usetListType)
    }
}