package com.example.helthcare_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helthcare_exercise.databinding.ActivityMainPageBinding

class MainPageActivity : AppCompatActivity() {

    private var mBinding: ActivityMainPageBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}