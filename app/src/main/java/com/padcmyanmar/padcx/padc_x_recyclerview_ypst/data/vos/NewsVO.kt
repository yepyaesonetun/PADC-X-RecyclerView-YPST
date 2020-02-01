package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos

import com.google.gson.annotations.SerializedName

data class NewsVO(
    @SerializedName("id") val id : Int = 0,
    @SerializedName("title") val title : String = "",
    @SerializedName("hero_image") val heroImage : String = "",
    @SerializedName("description") val description : String = "",
    @SerializedName("publication") val publication : PublicationVO? = null,
    @SerializedName("liked_users") val likedUsers : ArrayList<UserVO> = arrayListOf(),
    @SerializedName("comments") val comments : ArrayList<CommentVO> = arrayListOf()
)