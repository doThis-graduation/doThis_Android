package fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.healthcare_exercise.R
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
import java.text.SimpleDateFormat
import java.util.*


class ExerciseUploadFragment : Fragment() {

    private var viewProfile : View?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewProfile = inflater.inflate(R.layout.fragment_exercise_upload, container, false)

        //사용자 정보 text set
        infoSet()

        return viewProfile
    }

    private fun infoSet() {
        val name = arguments?.getString("name")
        val email = arguments?.getString("email")
        val str_uri = arguments?.getString("uri")
        val uri = Uri.parse(str_uri)
        var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
        this.viewProfile!!.video_view.setVideoURI(uri)
        this.viewProfile!!.video_view.start()
        this.viewProfile!!.tx_info.text = "E-mail: "+email+"\nUser: "+name+"\nDate: "+timeStamp
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