package com.example.mydagger2demokotlin.viewmodel

import androidx.lifecycle.*
import com.example.mydagger2demokotlin.data.model.Blog
import com.example.mydagger2demokotlin.data.model.Movie
import com.example.mydagger2demokotlin.data.model.User
import com.example.mydagger2demokotlin.data.retrofit.RetrofitBuilder
import com.example.mydagger2demokotlin.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel(){
    var list = MutableLiveData<ArrayList<Blog>>()
    var newList = arrayListOf<Blog>()
    var movieMutableList : MutableLiveData<ArrayList<Movie>> = MutableLiveData()
    private var _userId : MutableLiveData<String> = MutableLiveData()

    val user : LiveData<User> = Transformations
        .switchMap(_userId){
            Repository.getUser(it)
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

    fun add(blog : Blog){
        newList.add(blog)
        list.value = newList
    }

    fun remove(blog : Blog){
        newList.remove(blog)
        list.value = newList
    }

    fun getMovieList(){
        viewModelScope.launch(Dispatchers.IO) {
            val response =  RetrofitBuilder.apiService.getMovieList()
            movieMutableList.postValue(response)
        }
    }
}