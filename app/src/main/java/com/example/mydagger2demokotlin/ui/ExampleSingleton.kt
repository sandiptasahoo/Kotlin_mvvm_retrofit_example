package com.example.mydagger2demokotlin.ui

import com.example.mydagger2demokotlin.data.model.User

object ExampleSingleton {
    val singletonUser : User by lazy {
        User("sandipta9090@gmail.com", "sandiptas", "image.png")
    }
}