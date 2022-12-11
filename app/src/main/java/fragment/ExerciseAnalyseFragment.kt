package fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.databinding.FragmentExerciseAnalyseBinding
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_exercise_analyse.*
import kotlinx.android.synthetic.main.fragment_exercise_upload.*
import kotlinx.android.synthetic.main.fragment_exercise_upload.tx_userName
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
import com.google.firebase.storage.FirebaseStorage

class ExerciseAnalyseFragment : Fragment() {

    var uri : Uri? = null
    lateinit var binding:FragmentExerciseAnalyseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseAnalyseBinding.inflate(inflater, container, false)

        Toast.makeText(context, "분석 완료!",Toast.LENGTH_SHORT).show()

        var userName = arguments?.getString("name").toString()
        var method = arguments?.getString("method").toString()
        var str_uri = arguments?.getString("uri").toString()
        var path = arguments?.getString("path").toString()

        uri = Uri.parse(str_uri)

        binding.txUserName.text = userName
        binding.txMethod.text = method
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()

        var storage = FirebaseStorage.getInstance()
        var storageRef = storage.getReference()

        storageRef.child("temp/result/"+path+"_graph.png").downloadUrl.addOnSuccessListener{
            Glide.with(this)
                .load(it)
                .into(binding.imgGraph)
            Log.d("이미지", "graph 띄움")
        }
        storageRef.child("temp/result/"+path+"_good.png").downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .load(it)
                .into(binding.imgWorstPose)
            Log.d("이미지", "worst 띄움")
        }



        return binding.root
    }
}