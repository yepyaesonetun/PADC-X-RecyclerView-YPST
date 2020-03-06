package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos

sealed class Result {
    class Success<T>(val value : T)
    class Errror(val errorMessage : String)
}