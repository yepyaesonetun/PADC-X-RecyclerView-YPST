package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO

data class GetAllNewsResponse(
    @SerializedName("code") val code : Int  = 0,
    @SerializedName("message") val message : String = "",
    @SerializedName("data") val data: ArrayList<NewsVO>? = null
){
    fun isResponseOk() = (code == 200) && (data != null)
}