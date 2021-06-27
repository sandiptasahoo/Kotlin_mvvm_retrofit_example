package com.example.mydagger2demokotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydagger2demokotlin.R
import com.example.mydagger2demokotlin.data.model.Blog
import com.example.mydagger2demokotlin.data.model.Movie
import com.example.mydagger2demokotlin.viewmodel.MyViewModel

class RecyclerMovieAdapter (val arrayList: ArrayList<Movie>, val context : Context, val myViewModel: MyViewModel) : RecyclerView.Adapter<RecyclerMovieAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class MyViewHolder(binding: View) : RecyclerView.ViewHolder(binding){
        var blogTitle: TextView = binding.findViewById(R.id.title)
        fun bind(movie: Movie){
            blogTitle.text = movie.name
        }
    }
}