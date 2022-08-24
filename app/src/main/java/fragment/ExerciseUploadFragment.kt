package fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.healthcare_exercise.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
import java.text.SimpleDateFormat
import java.util.*


class ExerciseUploadFragment : Fragment() {

    private var viewProfile : View?=null
    var fbStorage : FirebaseStorage?=null
    var uri : Uri? = null
    lateinit var name: String
    lateinit var email: String
    var method = "unselected"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewProfile = inflater.inflate(R.layout.fragment_exercise_upload, container, false)

        fbStorage = FirebaseStorage.getInstance()

        //사용자 정보 text set
        infoSet()

        //storage에 동영상 upload
        this.viewProfile!!.btn_analyse.setOnClickListener(View.OnClickListener { funImageUpload(viewProfile!!) })

        //realtime database에 info upload

        return viewProfile
    }

    private fun infoSet() {
        name = arguments?.getString("name").toString()
        email = arguments?.getString("email").toString()
        val str_uri = arguments?.getString("uri")
        uri = Uri.parse(str_uri)
        var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
        this.viewProfile!!.video_view.setVideoURI(uri)
        this.viewProfile!!.video_view.start()
        this.viewProfile!!.tx_info.text = "E-mail: "+email+"\nUser: "+name+"\nDate: "+timeStamp
    }

    private fun funImageUpload(view:View){
        var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
        var imgFileName = "VIDEO_"+timeStamp+"_.mp4"
        var storageRef = fbStorage?.reference?.child(email)?.child(method)?.child(imgFileName)

        storageRef?.putFile(uri!!)?.addOnSuccessListener{
            Toast.makeText(context,"Video Uploaded", Toast.LENGTH_SHORT).show()
        }
    }
}