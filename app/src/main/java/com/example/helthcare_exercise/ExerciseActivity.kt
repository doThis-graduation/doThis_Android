package com.example.helthcare_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.helthcare_exercise.databinding.ActivityExerciseBinding
import com.example.helthcare_exercise.databinding.ActivityMainPageBinding
import fragment.ptFragment

class ExerciseActivity : AppCompatActivity() {

    private var mBinding: ActivityExerciseBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, ptFragment())
        transaction.commit()
    }

    fun ptList(view: View) {

    }
}