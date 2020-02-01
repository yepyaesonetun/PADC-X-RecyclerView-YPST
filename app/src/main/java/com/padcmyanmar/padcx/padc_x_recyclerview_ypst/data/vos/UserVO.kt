package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos

import com.google.gson.annotations.SerializedName

data class UserVO(
    @SerializedName("id") val id : Int = 0,
    @SerializedName("user_name") val userName : String = "",
    @SerializedName("email") val email: String = ""
)