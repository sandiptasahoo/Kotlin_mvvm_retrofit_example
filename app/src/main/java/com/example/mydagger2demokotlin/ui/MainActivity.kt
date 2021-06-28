package com.example.mydagger2demokotlin.ui

import android.content.Intent
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
import com.example.mydagger2demokotlin.viewmodel.GitHubReposViewModel
import com.example.mydagger2demokotlin.viewmodel.MyViewModel
import com.example.mydagger2demokotlin.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var btn : Button
    private lateinit var viewModel : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Singleton Example
        * If you rotate the screen or will go to some other app and come back
        * Same memory location will print
        * */
        print("DEBUG: ${ExampleSingleton.singletonUser.hashCode()}") //it will print the memory location of object
        val factory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(MyViewModel ::class.java)

        btn = findViewById(R.id.button)
        btn.setOnClickListener{
            val intent = Intent(this, UserGitHubRepoList::class.java)
            startActivity(intent)
        }

        /*viewModel.user.observe(this, Observer {
            println("DEBUG: $it");
        })*/
        //viewModel.setUserId("1")

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}