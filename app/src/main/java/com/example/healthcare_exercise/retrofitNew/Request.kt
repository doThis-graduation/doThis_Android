package com.example.healthcare_exercise.retrofitNew

import com.example.healthcare_exercise.fragment.ExerciseUploadFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Request {
    fun requestResponse(path: String, mCallback: ExerciseUploadFragment){
        val call = RetrofitClient.service.loadResponse(path.toString())

        call.enqueue(object: Callback<List<Data>> {
            override fun onResponse(
                call: Call<List<Data>>,
                response: Response<List<Data>>
            ) {
                if(response.isSuccessful){
                    // ExerciseUploadFragment 안에 정상 response 시 함수 만들기
                }
                else{
                    // mCallback.~~
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                //mCallback.~~()
            }
        })
    }
}