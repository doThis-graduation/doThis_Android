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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import retrofit.Data
import retrofit2.http.Url
import java.net.URL

class ExerciseAnalyseFragment : Fragment() {

    var uri : Uri? = null
    lateinit var binding:FragmentExerciseAnalyseBinding
    lateinit var bestAdapter: BestAdapter
    val datas = mutableListOf<BestData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseAnalyseBinding.inflate(inflater, container, false)

        Toast.makeText(context, "분석 완료!",Toast.LENGTH_SHORT).show()

        var email = arguments?.getString("email").toString()
        var userName = arguments?.getString("name").toString()
        var method = arguments?.getString("method").toString()
        var str_uri = arguments?.getString("uri").toString()
        var path = arguments?.getString("path").toString()
        path = "temp/image/"+path+"/"

        uri = Uri.parse(str_uri)

        binding.txUserName.text = userName
        binding.txMethod.text = method
        binding.videoView.setVideoURI(uri)
        binding.videoView.setMediaController((MediaController(context)))
        binding.videoView.start()

        var storage = FirebaseStorage.getInstance()
        var storageRef = storage.getReference()

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

// result 1
        binding.txResult1.text = "result 1"
        storageRef.child(path+"best1_user.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgUserBest1)
        }
        storageRef.child(path+"best1_pro.jpg").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgExpertBest1)
        }

// result 2
        binding.txResult1.text = "result 2"
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
        binding.txResult1.text = "result 3"
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
        binding.txResult1.text = "result 4"
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
        binding.txResult1.text = "result 5"
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

private fun getUserInfoFromFile(url: Uri): Data? {
    return Gson().fromJson(url.toString(), Data::class.java)
}