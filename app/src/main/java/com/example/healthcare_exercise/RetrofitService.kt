package com.example.healthcare_exercise

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    // https://www.~~/url/?query="msg" -> Json 형식으로 반환 받음
    @GET("/upload")
    fun analyseFin(@Query("query") searchTerm: String) : Call<JsonElement>

//    @POST("url") // 서버에 get 요청을 할 주소
//    fun callServer(@Path("path") path: String ): Call<String>
}