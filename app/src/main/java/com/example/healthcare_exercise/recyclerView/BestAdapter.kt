package com.example.healthcare_exercise.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthcare_exercise.R

class BestAdapter (private val context: Context): RecyclerView.Adapter<BestAdapter.ViewHolder>(){

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        private val result: TextView = itemView.findViewById(R.id.tx_result)
        private val uri: ImageView = itemView.findViewById(R.id.img_bestPose)

        fun bind(item: BestData){
            result.text = item.result.toString()
            Glide.with(itemView)
                .load(item.uri)
                .into(uri)
        }

    }

    var datas = mutableListOf<BestData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.result_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: BestAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }


}