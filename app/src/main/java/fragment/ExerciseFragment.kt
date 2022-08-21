package fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.ASSERT
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.healthcare_exercise.MainActivity
import com.example.healthcare_exercise.MainPageActivity
import com.example.healthcare_exercise.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

        //사용자 정보 가져오기
        Toast.makeText(context,"start",Toast.LENGTH_SHORT).show()
//        getUserProfile()
        Toast.makeText(context,"end",Toast.LENGTH_SHORT).show()




        //선택하기 버튼 listener
        viewProfile!!.btn_choose.setOnClickListener{
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
                img_pre.visibility=View.VISIBLE
                img_pre.setVideoURI(uriPhoto)
                img_pre.setMediaController((MediaController(context)))
                img_pre.start()
//                img_pre.setOnCompletionListener(mp->img_pre.start())
                btn_upload.visibility=View.VISIBLE

                //firebase에 업로드 하는 함수로 이동 -> upload fragment로 전환
                btn_upload.setOnClickListener(View.OnClickListener {
//                    funImageUpload(viewProfile!!)
                    val mainPageActivity = activity as MainPageActivity
                    mainPageActivity.setDataAtFragment(ExerciseUploadFragment(),"userName Test")
                })
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

//    private fun getUserProfile(){
//        val user = Firebase.auth.currentUser
//        Toast.makeText(context,"GET START",Toast.LENGTH_SHORT).show()
////        user?.let {
//            Toast.makeText(context,"GET INININ",Toast.LENGTH_SHORT).show()
//            val name = user?.displayName
//            val email = user?.email
//            val photoUrl = user?.photoUrl
//
////            val emailVerified = user.isEmailVerified
//
////            val uid = user.uid
//            Toast.makeText(context,name+"----"+email,Toast.LENGTH_SHORT).show()
//
//            Log.i("aa", "=============================%%%%%%#$%#%#$%#$%#$%#$%#$%#$%#$%")
//            Log.i("aa", "getUserProfile: "+email+" "+name)
////        }
//    }
}