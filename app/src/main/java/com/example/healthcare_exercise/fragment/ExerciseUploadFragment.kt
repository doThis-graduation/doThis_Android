package com.example.healthcare_exercise.fragment

import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.MediaController
import com.example.healthcare_exercise.activity.MainPageActivity
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.databinding.FragmentExerciseUploadBinding
import com.example.healthcare_exercise.retrofitNew.RetrofitClient
import com.google.firebase.storage.FirebaseStorage
import retrofit.RetrofitClient22
//import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
//import kotlinx.android.synthetic.main.fragment_exercise_upload.view.progress_bar
//import kotlinx.android.synthetic.main.fragment_exercise_upload.view.tx_progress
import java.text.SimpleDateFormat
import java.util.*
//import retrofit.RetrofitClient22
import retrofit2.Call
import retrofit2.Response

class ExerciseUploadFragment : Fragment() {

    lateinit var binding:FragmentExerciseUploadBinding

    private var viewProfile : View?=null
    var fbStorage : FirebaseStorage?=null
    var uri : Uri? = null
    lateinit var name: String
    lateinit var email: String
    lateinit var str_uri : String
    var method = "unselected"
    lateinit var path: String
    lateinit var time: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseUploadBinding.inflate(inflater, container, false)

        // 스토리지
        fbStorage = FirebaseStorage.getInstance()

        //사용자 정보 text set
        infoSet()

        //스피너 구현
        var methods = resources.getStringArray(R.array.methods)
        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, methods)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                method = methods.get(position)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //storage에 동영상 upload 및 분석 요청
        binding.btnAnalyse.setOnClickListener(View.OnClickListener {
            // firebase
            funImageUpload(binding)
//            requestResponse(path)
        })
//        return viewProfile
        return binding.root
    }

    private fun infoSet() {
        name = arguments?.getString("name").toString()
        email = arguments?.getString("email").toString()
        str_uri = arguments?.getString("uri").toString()
        uri = Uri.parse(str_uri)
        var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
        binding.videoView.setVideoURI(uri)
        binding.videoView.setMediaController((MediaController(context)))
        binding.videoView.start()
        binding.txUserName.text = name
        binding.txDate.text = timeStamp
    }

    private fun funImageUpload(binding:FragmentExerciseUploadBinding){
        //프로그레스바 loading 시작
        binding.progressBar.visibility = View.VISIBLE
        binding.txProgress.visibility = View.VISIBLE

        //layout 반투명화
        val paint = Paint()
        paint.alpha = 80
        binding.exerciseLayout.setBackgroundColor(paint.getColor())
        binding.videoView.setBackgroundColor(paint.getColor())

        //upload
        var timeStamp = SimpleDateFormat("yyMMddHHmm").format(Date())
        time = timeStamp.toString()
        if(method.equals("업로드할 운동을 선택해주세요")) method = "unselected"
        path = email+"/exercise_"+method+"_"+timeStamp
        var imgFileName = path+".mp4"
        var storageRef = fbStorage?.reference?.child("temp/video/user/")?.child(imgFileName)

//        thread(start = true){
//            Thread.sleep(500)
//            this.viewProfile!!.progress_bar.incrementProgressBy(15)
//            Thread.sleep(500)
//            this.viewProfile!!.progress_bar.incrementProgressBy(15)
//            Thread.sleep(500)
//            this.viewProfile!!.progress_bar.incrementProgressBy(15)
//        }



        //업로드, 업로드 확인
        storageRef?.putFile(uri!!)?.addOnSuccessListener{
            Log.d("파베","된다!!")
            //loading 끝냄
//            this.viewProfile!!.progress_bar.progress = 100
            requestResponse(path)
        }
    }

    //fragment  전환
    private fun changeFragment(){
        val activity = activity as MainPageActivity
        activity.setDataAtFragment(ExerciseAnalyseFragment(), str_uri, method, path, time)
    }

    //
    fun requestResponse(path: String){
//        val call = RetrofitClient22.service.loadResponse("temp/video/user/"+path)
        val call = RetrofitClient.service.loadResponse("temp/video/user/"+path)

        call.enqueue(object: retrofit2.Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if(response.isSuccessful){
                    var r = response.body().toString()
                    // 정상 response
                    Log.d("응답","complete: "+r)
                    if(r.equals("Result")){
                        Log.d("응답","된다!!")
                        // Log.e(TAG, "body: " + new Gson().toJson(response.body()));
                    }
                    changeFragment()
                }
                else{
                    // mCallback.~~
                    Log.d("응답","response fail: "+response.body().toString())
                    Log.d("응답","response fail: "+response.code())
                    changeFragment()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //mCallback.~~()
                Log.d("응답","server connect fail: "+t.toString())
                changeFragment()
            }
        })
    }
}