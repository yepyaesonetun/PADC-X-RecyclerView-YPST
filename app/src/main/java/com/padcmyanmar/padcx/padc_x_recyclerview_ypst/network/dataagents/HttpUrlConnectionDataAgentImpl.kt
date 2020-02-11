package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.responses.GetAllNewsResponse
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.BASE_URL
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.GET_NEWS
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.utils.PARAM_ACCESS_TOKEN
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

//object HttpUrlConnectionDataAgentImpl : NewsDataAgent {
//    override fun getAllNews(
//        accessToken: String
//    ) {
//        GetNewsTask(
//            accessToken
//        ).execute()
//    }
//
//    class GetNewsTask(
//        val accessToken: String) : AsyncTask<Void, Void, GetAllNewsResponse?>() {
//
//        override fun doInBackground(vararg params: Void?): GetAllNewsResponse? {
//            val url: URL
//            var reader: BufferedReader? = null
//            val stringBuilder: StringBuilder
//
//            try {
//                // create the HttpURLConnection
//                url = URL(BASE_URL + GET_NEWS) //1.
//
//                val connection = url.openConnection() as HttpURLConnection //2.
//
//                // just want to do an HTTP POST here
//                connection.requestMethod = "POST" //3.
//
//                // give it 15 seconds to respond
//                connection.readTimeout = 15 * 1000 //4. ms
//
//                connection.doInput = true //5.
//                connection.doOutput = true
//
//                //put the request parameter into NameValuePair list.
//                val params = ArrayList<NameValuePair>() //6.
//                params.add(
//                    BasicNameValuePair(
//                        PARAM_ACCESS_TOKEN,
//                        accessToken
//                    )
//                )
//
//                //write the parameters from NameValuePair list into connection obj.
//                val outputStream = connection.outputStream
//                val writer = BufferedWriter(
//                    OutputStreamWriter(outputStream, "UTF-8")
//                )
//                writer.write(getQuery(params))
//                writer.flush()
//                writer.close()
//                outputStream.close()
//
//                connection.connect() //7.
//
//                // read the output from the server
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                ) //8.
//                stringBuilder = StringBuilder()
//
//                for (line in reader.readLines()) {
//                    stringBuilder.append(line + "\n")
//                }
//                val responseString = stringBuilder.toString() //9
//
//                return Gson()
//                    .fromJson<GetAllNewsResponse>(responseString, GetAllNewsResponse::class.java)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.e("NewsError", e.message ?: "")
//            } finally {
//                // close the reader; this can throw an exception too, so
//                // wrap it in another try/catch block.
//                if (reader != null) {
//                    try {
//                        reader.close()
//                    } catch (ioe: IOException) {
//                        ioe.printStackTrace()
//                    }
//
//                }
//            }
//            return null
//        }
//
//        @Throws(UnsupportedEncodingException::class)
//        private fun getQuery(params: List<NameValuePair>): String {
//            val result = StringBuilder()
//            var first = true
//
//            for (pair in params) {
//                if (first)
//                    first = false
//                else
//                    result.append("&")
//
//                result.append(URLEncoder.encode(pair.getName(), "UTF-8"))
//                result.append("=")
//                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"))
//
//            }
//            return result.toString()
//
//
//        }
//
//        override fun onPostExecute(result: GetAllNewsResponse?) {
//            super.onPostExecute(result)
//        }
//
//
//    }
//}