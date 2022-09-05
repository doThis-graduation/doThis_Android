package fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import com.example.healthcare_exercise.MainPageActivity
import com.example.healthcare_exercise.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.android.synthetic.main.fragment_balance.view.*
import java.text.SimpleDateFormat
import java.util.*

class BalanceFragment : Fragment() {

    private var viewProfile : View?=null
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath : String

    var pickImageFromAlbum=0
    var uriPhoto : Uri? = null
    var fbStorage : FirebaseStorage?=null
    var email: String = "default email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewProfile = inflater.inflate(R.layout.fragment_balance, container, false)

        //카메라 촬영
        viewProfile!!.btn_camera.setOnClickListener(View.OnClickListener {
            //카메라 실행 코드
        })

        //동영상 선택
        viewProfile!!.btn_choose.setOnClickListener(View.OnClickListener {
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "video/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum)
        })

        return viewProfile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == pickImageFromAlbum){
            uriPhoto = data?.data
            img_pre.visibility = View.VISIBLE
            img_pre.setVideoURI(uriPhoto)
            img_pre.setMediaController(MediaController(context))
            img_pre.start()
            btn_upload.visibility = View.VISIBLE

            btn_upload.setOnClickListener(View.OnClickListener {
                fbStorage = FirebaseStorage.getInstance()

                var email = (activity as MainPageActivity).email
                var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
                var imgFileName = "VIDEO_"+timeStamp+"_.mp4"
                var storageRef = fbStorage?.reference?.child(email)?.child("balance")?.child(imgFileName)

                storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener {
                    Toast.makeText(context, "balance Video Uploaded", Toast.LENGTH_LONG).show()
                }
            })
        }
    }


    fun startCamera() {

    }
}