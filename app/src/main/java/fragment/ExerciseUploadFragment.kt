package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.healthcare_exercise.R
import java.text.SimpleDateFormat
import java.util.*


class ExerciseUploadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val name = arguments?.getString("name")
//        Toast.makeText(context,name, Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_exercise_upload, container, false)
    }

//    private fun funImageUpload(view:View){
//        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        var imgFileName = "VIDEO_"+timeStamp+"_.mp4"
//        var storageRef = fbStorage?.reference?.child("videos")?.child(imgFileName)
//
//        Toast.makeText(context,"In", Toast.LENGTH_SHORT).show()
//        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener{
//            Toast.makeText(context,"Image Uploaded", Toast.LENGTH_SHORT).show()
//        }
//    }
}