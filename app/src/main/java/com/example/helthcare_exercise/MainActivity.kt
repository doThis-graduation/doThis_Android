package com.example.helthcare_exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.helthcare_exercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //입력된 id, pw 읽어와서 파이어베이스와 비교. 로그인으로 넘어감.
    fun login(view: View) {
        val id = binding.etId.toString()
        val pw = binding.etPw.toString()

        val intent = Intent(this, MainPageActivity::class.java)
        startActivity(intent)
    }

    fun signIn(view: View) {

    }
}