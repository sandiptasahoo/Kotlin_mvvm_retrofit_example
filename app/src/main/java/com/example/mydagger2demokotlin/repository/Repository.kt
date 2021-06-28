package com.example.mydagger2demokotlin.repository

import androidx.lifecycle.LiveData
import com.example.mydagger2demokotlin.data.model.Repos
import com.example.mydagger2demokotlin.data.model.User
import com.example.mydagger2demokotlin.data.retrofit.RetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getGitHubRepos(userId: String): LiveData<Repos> {
        job = Job()
        return object : LiveData<Repos>(){
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {
                        val repos = RetrofitBuilder.apiService.getUserRepositories(userId, "1")
                        withContext(Main){
                            value = repos
                            it.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}