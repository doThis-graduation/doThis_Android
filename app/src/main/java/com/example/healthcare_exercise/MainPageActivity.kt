package com.example.healthcare_exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.healthcare_exercise.databinding.ActivityMainPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragment.BalanceFragment
import fragment.CommunityFragment
import fragment.ExerciseFragment
import fragment.MyPageFragment

class MainPageActivity : AppCompatActivity() {

    private var mBinding: ActivityMainPageBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //default fragment 설정
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame, ExerciseFragment())
        transaction.commit()

        //bottom navigation fragment 변경
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.run { setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.bn_exercise ->{
                    Toast.makeText(context, "E clicked", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, ExerciseFragment()).commit()
                }
                R.id.bn_balance ->{
                    Toast.makeText(context, "B clicked", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, BalanceFragment()).commit()
                }
                R.id.bn_community ->{
                    Toast.makeText(context, "C clicked", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, CommunityFragment()).commit()
                }
                R.id.bn_mypage ->{
                    Toast.makeText(context, "M clicked", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrame, MyPageFragment()).commit()
                }
            }
            true
        } }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}