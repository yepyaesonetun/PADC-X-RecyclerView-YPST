package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.PublicationVO

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-02-14.
 */

class PublicationTypeConverter {
    @TypeConverter
    fun toString(publicationVO: PublicationVO): String {
        return Gson().toJson(publicationVO)
    }

    @TypeConverter
    fun toPublication(publicationJson: String): PublicationVO {
        val publicationType = object : TypeToken<PublicationVO>() {}.type
        return Gson().fromJson(publicationJson, publicationType)
    }
}