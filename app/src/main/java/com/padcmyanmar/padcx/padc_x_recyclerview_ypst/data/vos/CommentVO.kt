package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos

import com.google.gson.annotations.SerializedName

data class CommentVO(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("comment_message") val commentMessage: String = "",
    @SerializedName("commented_user") val commentedUser: UserVO? = null
)
