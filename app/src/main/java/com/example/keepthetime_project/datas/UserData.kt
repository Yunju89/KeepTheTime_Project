package com.example.keepthetime_project.datas

data class UserData(
    val id: Int,
    val provider: String,
    val uid: String,
    val email: String,
    val ready_minute: Int,
    val nick_name: String,
    val app_maker: String,
    val profile_img: String,
    val created_at: String,
    val updated_at: String
)