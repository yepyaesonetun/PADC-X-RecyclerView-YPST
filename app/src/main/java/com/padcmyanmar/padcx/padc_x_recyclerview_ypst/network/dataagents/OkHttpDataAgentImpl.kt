//package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.dataagents
//
//import android.os.AsyncTask
//import com.google.gson.Gson
//import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
//import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.responses.GetAllNewsResponse
//import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.BASE_URL
//import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.EM_NULL_NEWS_RESPONSE
//import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.GET_NEWS
//import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.PARAM_ACCESS_TOKEN
//import okhttp3.FormBody
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.util.concurrent.TimeUnit
//
//object OkHttpDataAgentImpl : NewsDataAgent {
//
//    private val mClient: OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(15, TimeUnit.SECONDS)
//        .readTimeout(15, TimeUnit.SECONDS)
//        .writeTimeout(15, TimeUnit.SECONDS)
//        .build()
//
//    override fun getAllNews(
//        accessToken: String) {
//        GetNewsTask(mClient, accessToken).execute()
//    }
//
//    class GetNewsTask(
//        private val mOkHttpClient: OkHttpClient,
//        private val mAccessToken: String
//    ) :
//        AsyncTask<Void, Void, GetAllNewsResponse>() {
//
//        override fun doInBackground(vararg voids: Void): GetAllNewsResponse? {
//
//            val formBody = FormBody.Builder()
//                .add(PARAM_ACCESS_TOKEN, mAccessToken)
//                .build()
//
//            val request = Request.Builder()
//                .url(BASE_URL + GET_NEWS)
//                .post(formBody)
//                .build()
//
//            try {
//
//                val response = mOkHttpClient.newCall(request).execute()
//
//                if (response.isSuccessful) {
//                    response.body?.let {
//                        val responseString = it.string()
//                        return Gson().fromJson<GetAllNewsResponse>(
//                            responseString,
//                            GetAllNewsResponse::class.java
//                        )
//                    }
//                }
//
//            } catch (e: Exception) {
//
//            }
//
//            return null
//        }
//
//        override fun onPostExecute(allNewsResponse: GetAllNewsResponse?) {
//            super.onPostExecute(allNewsResponse)
//        }
//    }
//}