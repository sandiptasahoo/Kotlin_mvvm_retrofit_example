package com.example.mydagger2demokotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydagger2demokotlin.R
import com.example.mydagger2demokotlin.data.model.ReposItem

class RecyclerRepoAdapter(val arrayList: ArrayList<ReposItem>) : RecyclerView.Adapter<RecyclerRepoAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerRepoAdapter.MyViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerRepoAdapter.MyViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var blogTitle: TextView = itemView.findViewById(R.id.title)

        fun bind(item : ReposItem){
            blogTitle.text = item.name
        }
    }
}