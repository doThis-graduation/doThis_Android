package com.example.healthcare_exercise.fragment

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import com.example.healthcare_exercise.activity.MainPageActivity
import com.example.healthcare_exercise.databinding.FragmentBalanceBinding
import com.google.firebase.storage.FirebaseStorage
//import kotlinx.android.synthetic.main.fragment_balance.*
//import kotlinx.android.synthetic.main.fragment_balance.view.*
//import kotlinx.android.synthetic.main.fragment_exercise_upload.view.*
import java.text.SimpleDateFormat
import java.util.*
//import kotlinx.android.synthetic.main.fragment_balance.view.progress_bar as progress_bar1
//import kotlinx.android.synthetic.main.fragment_balance.view.tx_progress as tx_progress1

class BalanceFragment : Fragment() {
    lateinit var binding:FragmentBalanceBinding

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
        binding = FragmentBalanceBinding.inflate(inflater,container,false)

        //카메라 촬영
        binding.btnCamera.setOnClickListener(View.OnClickListener {
            //카메라 실행 코드
//            startCapture()
        })

        //동영상 선택
        binding.btnChoose.setOnClickListener(View.OnClickListener {
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "video/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum)
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //선택하기에 대한 실행
        if(requestCode == pickImageFromAlbum){
            uriPhoto = data?.data
            binding.imgPre.visibility = View.VISIBLE
            binding.imgPre.setVideoURI(uriPhoto)
            binding.imgPre.setMediaController(MediaController(context))
            binding.imgPre.start()
            binding.btnUpload.visibility = View.VISIBLE

            binding.btnUpload.setOnClickListener(View.OnClickListener {
                //progressBar 시작
                binding.progressBar.visibility = View.VISIBLE
                binding.txProgress.visibility = View.VISIBLE

                //layout 반투명화
                val paint = Paint()
                paint.alpha = 80
                binding.balanceLayout.setBackgroundColor(paint.getColor())
                binding.imgPre.setBackgroundColor(paint.getColor())

                fbStorage = FirebaseStorage.getInstance()

                var email = (activity as MainPageActivity).email
                var timeStamp = SimpleDateFormat("yyMMdd_HH:mm").format(Date())
                var date = SimpleDateFormat("yyMMdd").format(Date())
                var imgFileName = "VIDEO_"+timeStamp+"_.mp4"
                var storageRef = fbStorage?.reference?.child(email)?.child("balance")?.child(date)?.child(imgFileName)

                storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener {
                    Toast.makeText(context, "balance Video Uploaded", Toast.LENGTH_LONG).show()

                    //upload가 완료되면 balance page로 이동
                    changeFragment()
                }
            })
        }

        //사진촬영에 대한 실행
        if(requestCode == REQUEST_IMAGE_CAPTURE){

        }
    }


    //======================================새로운 startCapture()
//    fun startCapture(){
//        val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
//        val STORAGE_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//
//        //권한 플래그값 정의
//        val FLAG_PERM_CAMERA = 98
//        val FLAG_PERM_STORAGE = 99
//
//        //카메라와 갤러리를 호출하는 플래그
//        val FLAG_REQ_CAMERA = 101
//        val FLAG_REA_STORAGE = 102
//
//
//    }


//    fun checkPermission(permissions:Array<out String>, flag: Int):Boolean{
//        for(permission in permissions){
//            if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(this, permissions, flag)
//                return false
//            }
//        }
//        return true
//    }


    //======================================옛날 startCapture()
//    fun startCapture(){
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicktureIntent ->
//            takePicktureIntent.resolveActivity(packageManager)?.also {
//                val photoFile: File? = try {
//                    createImageFile()
//                }
//                catch(ex: IOException){
//                    null
//                }
//                photoFile?.also {
//                    val photoURI : Uri = FileProvider.getUriForFile(
//                        this,
//                        "org.techtown.capturepicture.fileprovider",
//                        it
//                    )
//                    takePicktureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                    startActivityForResult(takePicktureIntent, REQUEST_IMAGE_CAPTURE)
//                }
//            }
//        }
//    }
//
//    fun createImageFile(): File {
//
//        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//            "JPEG_${timeStamp}_",
//            ".jpg",
//            storageDir
//        ).apply{
//            currentPhotoPath = absolutePath
//        }
//    }

    //fragment  전환
    private fun changeFragment(){
        val activity = activity as MainPageActivity
        activity.setFragment(BalanceAnalyseFragment())
    }
}