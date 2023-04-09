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

class RecordAdapter (private val context: Context): RecyclerView.Adapter<RecordAdapter.ViewHolder>(){
    var datas = mutableListOf<RecordData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val num: TextView = itemView.findViewById(R.id.tx_num)
        private val uri: ImageView = itemView.findViewById(R.id.img_sim)
        private val part: TextView = itemView.findViewById(R.id.tx_part)
        private val date: TextView = itemView.findViewById(R.id.tx_date)

        fun bind(item: RecordData){
            num.text = item.num
            Glide.with(itemView).load(item.uri).into(uri)
            part.text = item.part
            date.text = item.date
        }

    }
}