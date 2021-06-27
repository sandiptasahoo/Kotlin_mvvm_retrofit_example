package com.example.mydagger2demokotlin.repository

import androidx.lifecycle.LiveData
import com.example.mydagger2demokotlin.data.model.User
import com.example.mydagger2demokotlin.data.retrofit.RetrofitBuilder
import kotlinx.coroutines.*

object Repository{

    var job : CompletableJob? = null

    fun getUser(userId : String) : LiveData<User> {
        job = Job()
        return object : LiveData<User>(){
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Dispatchers.Main){
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}