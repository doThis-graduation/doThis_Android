package com.example.healthcare_exercise.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    lateinit var binding:FragmentCommunityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)

        var userName = arguments?.getString("name").toString()
        binding.txUserName.text=userName

        /*
            realtime DB 에 연결하고,
            경로를 temp/record/example123@gmail_com  으로 설정하고
            json 형식으로
            key: result / value: 79% (평균 값)
            key: method / value: squat
            key: date / value: yymmdd
            로 들어있도록.
            List 형식으로 firebase 상의 모든 데이터를 읽어와야 할 것 같은데, 흠 어려울 것 같다. 잘 고민해보자.

            추가적으로 맨 위에 뜨는 '이번주 최고의 자세'는 어떻게 할지 고민 필요.
            1. 서버에서 따로 계산하여 realtime DB 에 쓰도록.
            2. 안드로이드에서 위에서 읽어온 값들 중, 1주일 내에 한 운동이 있다면 그 중 result 값이 가장 큰 값을 찾아서 띄워줌.



         */
        return binding.root
    }
}