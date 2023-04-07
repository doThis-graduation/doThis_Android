package fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.RecordAdapter
import com.example.healthcare_exercise.RecordData
import com.example.healthcare_exercise.databinding.FragmentCommunityBinding

class RecordFragment : Fragment() {

    lateinit var binding: FragmentCommunityBinding
    lateinit var recordAdapter: RecordAdapter
    val datas = mutableListOf<RecordData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)

        initRecordRecycler()

        return binding.root
    }

    private fun initRecordRecycler(){
        recordAdapter = RecordAdapter(this)
        rv_record.adapter = recordAdapter

        datas.apply{
            add(RecordData(img = R.drawable.ic_launcher_foreground), num=getItemCount())

            recordAdapter.datas = datas
            recordAdapter.notifyDataSetChanged()
        }
    }
}