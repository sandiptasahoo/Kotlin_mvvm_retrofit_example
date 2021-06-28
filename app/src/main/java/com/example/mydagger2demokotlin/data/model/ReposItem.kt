package com.example.mydagger2demokotlin.data.model

data class ReposItem(
    val description: String,
    val full_name: String,
    val id: Int,
    val name: String,
    val owner: Owner,
    val updated_at: String,
    val url: String
)