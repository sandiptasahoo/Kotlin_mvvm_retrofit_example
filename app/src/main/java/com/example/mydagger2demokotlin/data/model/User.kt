package com.example.mydagger2demokotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("email")
    var email: String?,

    @Expose
    @SerializedName("username")
    var username: String?,
    @Expose
    @SerializedName("image")
    var image: String?
) {
    override fun toString(): String {
        return "User(email=$email, username=$username, image=$image)"
    }
}