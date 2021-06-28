package com.example.mydagger2demokotlin.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydagger2demokotlin.R
import com.example.mydagger2demokotlin.data.model.Blog
import com.example.mydagger2demokotlin.viewmodel.GitHubReposViewModel
import com.example.mydagger2demokotlin.viewmodel.MyViewModel
import com.example.mydagger2demokotlin.viewmodel.ViewModelFactory

class UserGitHubRepoList : AppCompatActivity() {

    private var linearLayoutManager = LinearLayoutManager(this)
    private lateinit var viewModel : GitHubReposViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        val factory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(GitHubReposViewModel ::class.java)
        btn = findViewById(R.id.button)
        btn.setOnClickListener{
            //viewModel.getMovieList()
        }
        initializeAdapter()
        viewModel.setUserId("sandiptasahoo")
    }

    private fun initializeAdapter() {
        recyclerView.layoutManager = linearLayoutManager
        observeRepoData()
    }

    private fun observeRepoData(){
        viewModel.repo.observe(this, Observer {
            recyclerView.adapter = RecyclerRepoAdapter(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}