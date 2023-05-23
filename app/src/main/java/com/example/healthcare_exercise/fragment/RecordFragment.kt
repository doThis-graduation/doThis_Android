package com.example.healthcare_exercise.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthcare_exercise.recyclerView.RecordAdapter
import com.example.healthcare_exercise.recyclerView.RecordData
import com.example.healthcare_exercise.databinding.FragmentRecordBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.ktx.database


class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding
    lateinit var recordAdapter: RecordAdapter
    val datas = mutableListOf<RecordData>()
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

//        // Firebase realTime Database 연결
//        Log.d("파베", "시작")
//        // realTime DB
//        database = Firebase.database.getReference().child("resultPath");





        // recycler view 호출
        context?.let { initRecordRecycler(it) }

        return binding.root
    }

    private fun initRecordRecycler(context: Context){
        // Firebase realTime Database 연결
        Log.d("파베", "시작")
        // realTime DB
        database = Firebase.database.getReference().child("/temp/result/drj9802@gmail_com/exercise_Squat_2305092239");

        // Adapter 선언
        recordAdapter = RecordAdapter(context)
        binding.rvRecord.adapter = recordAdapter

        database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(data in dataSnapshot.children){
//                    val modelResult = data.getValue(RecordData::class.java)
                    datas.add(RecordData(result=" 89%", num=recordAdapter.itemCount.toString(), method = "SQUAT1", date = "yymmdd"))
                }
            }
        })

//        datas.apply{
//            add(RecordData(result=" 89%", num=recordAdapter.itemCount.toString(), part = "SQUAT1", date = "yymmdd"))
//            add(RecordData(result=" 36%", num=recordAdapter.itemCount.toString(), part = "SQUAT2", date = "yymmdd"))
//            add(RecordData(result=" 75%", num=recordAdapter.itemCount.toString(), part = "SQUAT3", date = "yymmdd"))
//            add(RecordData(result=" 66%", num=recordAdapter.itemCount.toString(), part = "SQUAT4", date = "yymmdd"))

            recordAdapter.datas = datas
            recordAdapter.notifyDataSetChanged()
//        }
    }
}