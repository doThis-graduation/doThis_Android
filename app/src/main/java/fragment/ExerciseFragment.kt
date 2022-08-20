package fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.healthcare_exercise.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_exercise.*
import kotlinx.android.synthetic.main.fragment_exercise.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class ExerciseFragment : Fragment() {

    private var viewProfile : View?=null
    var pickImageFromAlbum = 0
    var fbStorage : FirebaseStorage?=null
    var uriPhoto : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewProfile = inflater.inflate(R.layout.fragment_exercise, container, false)

        fbStorage = FirebaseStorage.getInstance()

        viewProfile!!.btn_upload.setOnClickListener{
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
//            photoPickerIntent.type = "image/*"
            photoPickerIntent.type = "video/*"
            Toast.makeText(context,"success",Toast.LENGTH_SHORT).show()
            startActivityForResult(photoPickerIntent, pickImageFromAlbum)
        }
        return viewProfile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Toast.makeText(context,"selected",Toast.LENGTH_SHORT).show()
        if(requestCode == pickImageFromAlbum){
//            if(requestCode == Activity.RESULT_OK){
                Toast.makeText(context,"in set",Toast.LENGTH_SHORT).show()
                //img_pre 미리보기
                uriPhoto = data?.data
//                img_pre.setImageURI(uriPhoto)
                img_pre.setVideoURI(uriPhoto)

                //firebase에 업로드 하는 함수로 이동
                funImageUpload(viewProfile!!)
//            }
        }
    }

    private fun funImageUpload(view:View){
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imgFileName = "VIDEO_"+timeStamp+"_.mp4"
        var storageRef = fbStorage?.reference?.child("videos")?.child(imgFileName)

        Toast.makeText(context,"In",Toast.LENGTH_SHORT).show()
        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener{
            Toast.makeText(context,"Image Uploaded",Toast.LENGTH_SHORT).show()
        }
    }
}