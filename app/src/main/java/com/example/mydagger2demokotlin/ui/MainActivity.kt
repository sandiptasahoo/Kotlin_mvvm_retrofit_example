package com.example.mydagger2demokotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydagger2demokotlin.R
import com.example.mydagger2demokotlin.data.model.Blog
import com.example.mydagger2demokotlin.viewmodel.MyViewModel
import com.example.mydagger2demokotlin.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var linearLayoutManager = LinearLayoutManager(this)
    private lateinit var viewModel : MyViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var btn : Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Singleton Example
        * If you rotate the screen or will go to some other app and come back
        * Same memory location will print
        * */
        print("DEBUG: ${ExampleSingleton.singletonUser.hashCode()}") //it will print the memory location of object

        recyclerView = findViewById(R.id.recycler)
        val factory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(MyViewModel ::class.java)
        btn = findViewById(R.id.button)
        editText = findViewById(R.id.titletxt)
        btn.setOnClickListener{
            //addData()
            //viewModel.getMovieList()
            viewModel.setUserId("1")
        }
        /*initializeAdapter()*/

        viewModel.user.observe(this, Observer {
            println("DEBUG: $it");
        })


    }

    private fun initializeAdapter() {
        recyclerView.layoutManager = linearLayoutManager
        observeMovieData()
    }

    private fun observeData(){
        viewModel.list.observe(this, Observer {
            recyclerView.adapter = RecyclerAdapter(it, this, viewModel)
        })
    }

    private fun observeMovieData(){
        viewModel.movieMutableList.observe(this, Observer {
            recyclerView.adapter = RecyclerMovieAdapter(it, this, viewModel)
        })
    }

    private fun addData(){
        val data : String?
        data = editText.text.toString()
        if(data.isNotEmpty()){
            val blog = Blog(data)
            viewModel.add(blog)
            editText.text.clear()
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}