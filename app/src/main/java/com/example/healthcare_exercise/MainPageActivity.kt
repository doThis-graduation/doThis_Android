package com.example.healthcare_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare_exercise.databinding.ActivityMainPageBinding
import fragment.ExerciseFragment

class MainPageActivity : AppCompatActivity() {

    private var mBinding: ActivityMainPageBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val transaction = supportFragmentManager.beginTransaction().add(R.id.mainFrame, exerciseFragment())
//        transaction.commit()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame, ExerciseFragment())
        transaction.commit()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}