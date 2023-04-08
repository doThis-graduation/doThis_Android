package fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Log.ASSERT
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import com.example.healthcare_exercise.MainActivity
import com.example.healthcare_exercise.MainPageActivity
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.databinding.FragmentExerciseBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
//import kotlinx.android.synthetic.main.fragment_balance.view.*
//import kotlinx.android.synthetic.main.fragment_exercise.*
//import kotlinx.android.synthetic.main.fragment_exercise.view.*
//import kotlinx.android.synthetic.main.fragment_exercise.view.btn_choose
//import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest
import kotlin.reflect.typeOf

class ExerciseFragment : Fragment() {
    lateinit var binding: FragmentExerciseBinding

    var pickImageFromAlbum = 0
    var uriPhoto : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)

        //선택하기 버튼 listener
        binding.btnChoose.setOnClickListener{
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "video/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum)
        }

        //카메라 버튼 listener
//        viewProfile!!.btn_camera.setOnClickListener {  }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //선택하기에 대한 결과
        if(requestCode == pickImageFromAlbum){
//            if(requestCode == Activity.RESULT_OK){
                //img_pre 미리보기

                uriPhoto = data?.data
                binding.imgPre.visibility=View.VISIBLE
                binding.imgPre.setVideoURI(uriPhoto)
                binding.imgPre.setMediaController((MediaController(context)))
                binding.imgPre.start()
//                img_pre.setOnCompletionListener(mp->img_pre.start()) //반복자동재생코드
                binding.btnUpload.visibility=View.VISIBLE

                //firebase에 업로드 하는 함수로 이동 -> upload fragment로 전환
                binding.btnUpload.setOnClickListener(View.OnClickListener {
                    val strUri = uriPhoto.toString()
                    val mainPageActivity = activity as MainPageActivity
                    mainPageActivity.setDataAtFragment(ExerciseUploadFragment(), strUri, "", "")
                })
//            }
        }
    }
}