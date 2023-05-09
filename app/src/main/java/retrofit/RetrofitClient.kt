package retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
//    private val okHttpClient = OkHttpClient.Builder()
//        .connectTimeout(240, TimeUnit.SECONDS)
//        .readTimeout(240, TimeUnit.SECONDS)
//        .writeTimeout(240, TimeUnit.SECONDS)
////        .connectTimeout(1, TimeUnit.SECONDS)
////        .readTimeout(1, TimeUnit.SECONDS)
////        .writeTimeout(1, TimeUnit.SECONDS)
//        .build()
//
//    private const val baseUrl = "http://58.127.238.25:5000/"
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val service = retrofit.create(RetrofitService::class.java)
}