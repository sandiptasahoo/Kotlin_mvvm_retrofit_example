package com.example.mydagger2demokotlin.data.retrofit

import com.example.mydagger2demokotlin.data.model.Movie
import com.example.mydagger2demokotlin.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movielist.json")
    suspend fun getMovieList(): ArrayList<Movie>

    @GET("placeholder/user/{userId}")
    suspend fun getUser(
        @Path("userId") userId : String
    ): User
}