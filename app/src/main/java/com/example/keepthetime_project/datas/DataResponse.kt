package com.example.keepthetime_project.datas

data class DataResponse(
    val token: String,
    val user: UserData,
    val friends : List<UserData>
)