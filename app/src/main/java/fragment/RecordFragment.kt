package fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthcare_exercise.R
import com.example.healthcare_exercise.RecordAdapter
import com.example.healthcare_exercise.RecordData
import com.example.healthcare_exercise.databinding.FragmentCommunityBinding
import com.example.healthcare_exercise.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding
    lateinit var recordAdapter: RecordAdapter
    val datas = mutableListOf<RecordData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

        context?.let { initRecordRecycler(it) }

        return binding.root
    }

    private fun initRecordRecycler(context: Context){
        recordAdapter = RecordAdapter(context)
        binding.rvRecord.adapter = recordAdapter

        val stUri = "content://com.google.android.apps.photos.contentprovider/-1/2/content%3A%2F%2Fmedia%2Fexternal%2Fvideo%2Fmedia%2F38/ORIGINAL/NONE/video%2Fmp4/375404404"
        datas.apply{
            add(RecordData(uri = Uri.parse(stUri), num=recordAdapter.itemCount.toString(), part = "SQUAT1", date = "yymmdd"))
            add(RecordData(uri = Uri.parse(stUri), num=recordAdapter.itemCount.toString(), part = "SQUAT2", date = "yymmdd"))
            add(RecordData(uri = Uri.parse(stUri), num=recordAdapter.itemCount.toString(), part = "SQUAT3", date = "yymmdd"))
            add(RecordData(uri = Uri.parse(stUri), num=recordAdapter.itemCount.toString(), part = "SQUAT4", date = "yymmdd"))

            recordAdapter.datas = datas
            recordAdapter.notifyDataSetChanged()
        }
    }
}