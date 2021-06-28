package com.example.mydagger2demokotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel ::class.java)){
            return MyViewModel() as T
        }else if (modelClass.isAssignableFrom(GitHubReposViewModel ::class.java)){
            return GitHubReposViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}