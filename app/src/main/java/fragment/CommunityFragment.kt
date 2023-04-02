package fragment

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

        return binding.root
    }
}