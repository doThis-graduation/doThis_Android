package fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.databinding.FragmentExerciseAnalyseBinding
import kotlinx.android.synthetic.main.fragment_exercise_analyse.*
import kotlinx.android.synthetic.main.fragment_exercise_upload.*
import kotlinx.android.synthetic.main.fragment_exercise_upload.tx_userName
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*

class ExerciseAnalyseFragment : Fragment() {

    var uri : Uri? = null
    lateinit var binding:FragmentExerciseAnalyseBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExerciseAnalyseBinding.inflate(inflater, container, false)

        var userName = arguments?.getString("name").toString()
        var method = arguments?.getString("method").toString()
        var str_uri = arguments?.getString("uri").toString()
        uri = Uri.parse(str_uri)

        binding.txUserName.text = userName
        binding.txMethod.text = method
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()


        return binding.root
    }
}