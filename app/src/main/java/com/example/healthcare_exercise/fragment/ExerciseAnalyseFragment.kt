package com.example.healthcare_exercise.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.healthcare_exercise.databinding.FragmentExerciseAnalyseBinding
import com.example.healthcare_exercise.recyclerView.BestAdapter
import com.example.healthcare_exercise.recyclerView.BestData
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit.Data
import retrofit.PostModel
import retrofit2.http.Url
import java.net.URL

class ExerciseAnalyseFragment : Fragment() {

    var uri : Uri? = null
    lateinit var binding:FragmentExerciseAnalyseBinding
//    lateinit var bestAdapter: BestAdapter
//    val datas = mutableListOf<BestData>()
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseAnalyseBinding.inflate(inflater, container, false)

        Toast.makeText(context, "분석 완료!",Toast.LENGTH_SHORT).show()

        // get data from ExerciseUploadFragment through MainPageActivity
        var email = arguments?.getString("email").toString()
        var token = email.split('.');
        var userName = arguments?.getString("name").toString()
        var method = arguments?.getString("method").toString()
        var time = arguments?.getString("time").toString()
        var str_uri = arguments?.getString("uri").toString()
        var path = arguments?.getString("path").toString() // 기존에 넘어 오는 path

        // path for storage
        path = "temp/image/"+path+"/"   // 원래 코드에 적용 시킬 path
        path = "temp/image/drj9802@gmail.com/exercise_Squat_2304120145/"    // 임시 path
        // path for realtime DB
        var resultPath = "result/"+path // 원래 resultPath
        resultPath = "result/"+token[0]+"_com/exercise_"+method+"_"+time    // 최종 적용시킬 resultPath
        resultPath = "result/example123@gmail_com/exercise_squat_2304112205"    // 임시 resultPath

        // temp/result/drj9802@gmail.com/exercise_Squat_2304120140/.json
        // temp/result/temp/image/drj9802@gmail.com/exercise_Squat_2304120140/.json

        uri = Uri.parse(str_uri)

        binding.txUserName.text = userName
        binding.txMethod.text = method
        binding.videoView.setVideoURI(uri)
        binding.videoView.setMediaController((MediaController(context)))
        binding.videoView.start()

        var storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference
////////////////////////////////////////////////////////////
        //original

        // getReference("temp") 없이 그냥 resultPath 에 temp를 포함시켜서 child로 접근 해보기...!!

////////////////////////////////////////////////////////////
        Log.d("파베", "시작")
        // realTime DB
        database = Firebase.database.getReference("temp")
        // json file path: tmep / result / email / exercise_[method]_[timestamp].json
        database.child(resultPath).child("result").get().addOnSuccessListener {
            Log.i("파베", "Got value ${it.value.toString()}")
            binding.txResult.text=it.value.toString()+"%"
        }.addOnFailureListener {
            Log.e("파베", "Error getting data", it)
        }

        database.child(resultPath).child("result1").get().addOnSuccessListener{
            Log.i("파베", "Got value ${it.value.toString()}")
            binding.txResult1.text=it.value.toString()+"%"
        }.addOnFailureListener{
            Log.e("파베","Error getting data 1", it)
        }

        database.child(resultPath).child("result2").get().addOnSuccessListener{
            Log.i("파베","Got value ${it.value.toString()}")
            binding.txResult2.text=it.value.toString()+"%"
        }.addOnFailureListener{
            Log.e("파베","Error getting data 2", it)
        }

        database.child(resultPath).child("result3").get().addOnSuccessListener{
            Log.i("파베","Got value ${it.value.toString()}")
            binding.txResult3.text=it.value.toString()+"%"
        }.addOnFailureListener{
            Log.e("파베","Error getting data 3", it)
        }

        database.child(resultPath).child("result4").get().addOnSuccessListener{
            Log.i("파베","Got value ${it.value.toString()}")
            binding.txResult4.text=it.value.toString()+"%"
        }.addOnFailureListener{
            Log.e("파베","Error getting data 4", it)
        }

        database.child(resultPath).child("result5").get().addOnSuccessListener{
            Log.i("파베","Got value ${it.value.toString()}")
            binding.txResult5.text=it.value.toString()+"%"
        }.addOnFailureListener{
            Log.e("파베","Error getting data 5", it)
        }
        Log.d("파베", "끝")
////////////////////////////////////////////////////////////
        // new



////////////////////////////////////////////////////////////
//        val postListener = object: ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot){
//
//            }
//        }
////////////////////////////////////////////////////////////




//        storageRef.child(email+"/"+path+"_result/"+path+".json").downloadUrl.addOnSuccessListener {
//            var result: Data
//            result = getUserInfoFromFile(it)!!
//            Log.d("결과",result.toString())
//        }

        storageRef.child(path+"graph.png").downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .load(it)
                .into(binding.imgGraph)
            Log.d("이미지", "graph 띄움")
        }

//        context?.let{initBestRecycler(it)}

//        storageRef.child(email+"/"+path+"_result/"+path+"_worst1.png").downloadUrl.addOnSuccessListener {
//            Glide.with(this)
//                .load(it)
//                .into(binding.imgWorstPose1)
//            Log.d("이미지", "worst1 띄움")
//        }
//        storageRef.child(email+"/"+path+"_result/"+path+"_worst2.png").downloadUrl.addOnSuccessListener {
//            Glide.with(this)
//                .load(it)
//                .into(binding.imgWorstPose2)
//            Log.d("이미지", "worst2 띄움")
//        }storageRef.child(email+"/"+path+"_result/"+path+"_worst1.png").downloadUrl.addOnSuccessListener

// result json get
//        lateinit var result: PostModel
//        storageRef.child(jsonPath).downloadUrl.addOnSuccessListener {
//            Log.d("결과_json","json 다운로드 성공 1")
//            result = getUserInfoFromFile(it)!!
//            Log.d("결과_json","json uri: "+ result.toString())
//        }
//        Log.d("결과",result.result.toString())
//        binding.txResult.text = result.result.toString()
        
// result 1
//        binding.txResult1.text = result.result1.toString()
        // /temp/image/example123@gmail.com/exercise_squat_2304112205
        Log.d("스토리지", "받아오기 시작!!!!=> "+ path+"best1_user.jpg")
        storageRef.child(path+"best1_user.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgUserBest1)
            Log.d("스토리지", "성공@@")
        }
        storageRef.child(path+"best1_pro.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgExpertBest1)
        }

// result 2
//        binding.txResult1.text = result.result2.toString()
        storageRef.child(path+"best2_user.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgUserBest2)
        }
        storageRef.child(path+"best2_pro.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgExpertBest2)
        }

// result 3
//        binding.txResult1.text = result.result3.toString()
        storageRef.child(path+"best3_user.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgUserBest3)
        }
        storageRef.child(path+"best3_pro.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgExpertBest3)
        }

// result 4
//        binding.txResult1.text = result.result4.toString()
        storageRef.child(path+"best4_user.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgUserBest4)
        }
        storageRef.child(path+"best4_pro.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgExpertBest4)
        }

// result 5
//        binding.txResult1.text = result.result5.toString()
        storageRef.child(path+"best5_user.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgUserBest5)
        }
        storageRef.child(path+"best5_pro.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgExpertBest5)
        }

        return binding.root
    }

//    private fun initBestRecycler(context: Context){
//        bestAdapter = BestAdapter(context)
//        binding.rvBest.adapter = bestAdapter
//        val stUri = "content://com.google.android.apps.photos.contentprovider/-1/2/content%3A%2F%2Fmedia%2Fexternal%2Fvideo%2Fmedia%2F38/ORIGINAL/NONE/video%2Fmp4/375404404"
//        datas.apply{
//            add(BestData(result = 85, uri=Uri.parse(stUri)))
//            add(BestData(result = 65, uri=Uri.parse(stUri)))
//            add(BestData(result = 75, uri=Uri.parse(stUri)))
//
//            bestAdapter.datas = datas
//            bestAdapter.notifyDataSetChanged()
//        }
//
//    }
}

//"temp/result/"+path+"_graph.png"
//"temp/result/"+path+"_good.png"

//private fun getUserInfoFromFile(json: JsonObject): PostModel? {
//    Log.d("결과_json","json uri: "+ url.toString())
//    return Gson().fromJson(json, PostModel::class.java)
//}





// temp/result/drj9802@gmail.com/exercise_Squat_2304120140/.json
// temp/result/temp/image/drj9802@gmail.com/exercise_Squat_2304120140/.json