package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthcare_exercise.R
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.android.synthetic.main.fragment_balance.view.*

class BalanceFragment : Fragment() {

    private var viewProfile : View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewProfile = inflater.inflate(R.layout.fragment_balance, container, false)

        viewProfile!!.btn_camera.setOnClickListener(View.OnClickListener {
            settingPermission()
            startCamera()
        })

        return viewProfile
    }


    fun settingPermission() {

    }

    fun startCamera() {

    }
}