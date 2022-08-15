package com.example.healthcare_exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcare_exercise.databinding.ActivityMainPageBinding
import com.google.firebase.auth.FirebaseAuth

class MainPageActivity : AppCompatActivity() {

    private var mBinding: ActivityMainPageBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    fun exerciseClicked(view: View) {
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }

    fun logout(view: View) {
        auth.signOut()
        var intent=Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}