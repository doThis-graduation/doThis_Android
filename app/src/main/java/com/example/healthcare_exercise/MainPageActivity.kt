package com.example.healthcare_exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcare_exercise.databinding.ActivityMainPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainPageActivity : AppCompatActivity() {

    private var mBinding: ActivityMainPageBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient:GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    fun exerciseClicked(view: View) {
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }

    fun logout(view: View) {
        var intent=Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}