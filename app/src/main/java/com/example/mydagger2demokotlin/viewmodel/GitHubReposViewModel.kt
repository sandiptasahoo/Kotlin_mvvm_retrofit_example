package com.example.mydagger2demokotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mydagger2demokotlin.data.model.Repos
import com.example.mydagger2demokotlin.repository.Repository

class GitHubReposViewModel : ViewModel() {
    private var _userId : MutableLiveData<String> = MutableLiveData()

    val repo : LiveData<Repos> = Transformations
        .switchMap(_userId){
            Repository.getGitHubRepos(it)
        }

    fun setUserId(userId : String){
        val update = userId
        if(_userId.value == update)
            return
        _userId.value = update
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}