package fragment

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
import android.widget.Toast
import com.example.healthcare_exercise.MainPageActivity
import com.example.healthcare_exercise.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.progress_bar
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.tx_progress
import retrofit.Data
import retrofit.Request
import java.text.SimpleDateFormat
import java.util.*
import retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class ExerciseUploadFragment : Fragment() {

    private var viewProfile : View?=null
    var fbStorage : FirebaseStorage?=null
    var uri : Uri? = null
    lateinit var name: String
    lateinit var email: String
    lateinit var str_uri : String
    var method = "unselected"
    lateinit var path: String
    val Okay: String = "okay"
    val Fail: String = "fail"

    //
    private val request = Request()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewProfile = inflater.inflate(R.layout.fragment_exercise_upload, container, false)

        fbStorage = FirebaseStorage.getInstance()

        //사용자 정보 text set
        infoSet()

        //스피너 구현
        var methods = resources.getStringArray(R.array.methods)
        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, methods)
        this.viewProfile!!.spinner.adapter = adapter

        this.viewProfile!!.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                method = methods.get(position)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //storage에 동영상 upload 및 분석 요청
        this.viewProfile!!.btn_analyse.setOnClickListener(View.OnClickListener {
            // firebase
            funImageUpload(viewProfile!!)
            requestResponse(path)

            //<----------------------------------------------------

//            request.requestResponse(path,this)
//
//            fun loadComplete(data: Data){
//                Log.d("응답","complete")
//            }
//            fun responseIsNotSuccessful(data: Data){
//                Log.d("응답","response fail")
//            }
//            fun loadFail(){
//                Log.d("응답","server connect fail")
//            }


            //<----------------------------------------------------
//            // server 에 path 보내고, response 받음
//            Log.d("서버","호출 시작예정")
//            RetrofitManager.instance.analyseFin(path = path, completion = {
//                // wait response
//                // 방법1. response 가 도착했음을 인식했을 때, 비교 후 changeFragment. 그때까지는 loading
//                // 방법2. response 가 도착할 만한 충분한 시간을 sleep 하고, changeFragment. 그때까지는 loading
//                response ->
//                if(response==Okay){
//                    Log.d("서버","호출 성공")
//                    sleep(2000)
//                    changeFragment()}
//                else {
//                    Log.d("서버","호출 실패")
//                    sleep(2000)
//                    changeFragment()}
//
////                response ->
////                when(response){
////                    Okay ->{
////                        Toast.makeText(context, "api 호출 성공입니다", Toast.LENGTH_SHORT).show()
////                        Log.d("서버","호출 성공")
////                        changeFragment()
////                    }
////                    Fail ->{
////                        Toast.makeText(context, "api 호출 에러입니다", Toast.LENGTH_SHORT).show()
////                        Log.d("서버","호출 실패")
////                        changeFragment()
////                    }
//
//            })
//            //---------------------------------------------------->
        })
        return viewProfile
    }

    private fun infoSet() {
        name = arguments?.getString("name").toString()
        email = arguments?.getString("email").toString()
        str_uri = arguments?.getString("uri").toString()
        uri = Uri.parse(str_uri)
        var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
        this.viewProfile!!.video_view.setVideoURI(uri)
        this.viewProfile!!.video_view.start()
        this.viewProfile!!.tx_userName.text = name
        this.viewProfile!!.tx_date.text = timeStamp
    }

    private fun funImageUpload(view:View){
        //프로그레스바 loading 시작
        this.viewProfile!!.progress_bar.visibility = View.VISIBLE
        this.viewProfile!!.tx_progress.visibility = View.VISIBLE

        //layout 반투명화
        val paint = Paint()
        paint.alpha = 80
        this.viewProfile!!.exercise_layout.setBackgroundColor(paint.getColor())
        this.viewProfile!!.video_view.setBackgroundColor(paint.getColor())

        //upload
        var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
        var date = SimpleDateFormat("yyMMdd").format(Date())
        var imgFileName = "VIDEO_"+timeStamp+"_.mp4"
        if(method.equals("업로드할 운동을 선택해주세요")) method = "unselected"
        var storageRef = fbStorage?.reference?.child(email)?.child("exercise")?.child(method)?.child(date)?.child(imgFileName)
//        path = email+"/exercise/"+method+"/"+date
        path = "${email}/exercise/${method}/date"

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
            //loading 끝냄
//            this.viewProfile!!.progress_bar.progress = 100
            //확인 메세지 출력
//            Toast.makeText(context,"exercise Video Uploaded_"+name+"_"+method, Toast.LENGTH_SHORT).show()
            //fragment 전환
//            changeFragment()
        }
    }

    //fragment  전환
    private fun changeFragment(){
        val activity = activity as MainPageActivity
        activity.setDataAtFragment(ExerciseAnalyseFragment(), str_uri, method)
    }


    //
    fun requestResponse(path: String){
        val call = RetrofitClient.service.loadResponse(path)

        call.enqueue(object: retrofit2.Callback<Data> {
            override fun onResponse(
                call: Call<Data>,
                response: Response<Data>
            ) {
                if(response.isSuccessful){
                    // ExerciseUploadFragment 안에 정상 response 시 함수 만들기
                    Log.d("응답","complete"+response.body().toString())
                    changeFragment()
                }
                else{
                    // mCallback.~~
                    Log.d("응답","response fail"+response.body().toString())
                    changeFragment()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                //mCallback.~~()
                Log.d("응답","server connect fail"+t.toString())
                changeFragment()
            }
        })
    }
}