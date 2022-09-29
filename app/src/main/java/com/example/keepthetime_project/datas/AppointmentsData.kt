package com.example.keepthetime_project.datas

import java.util.*

data class AppointmentsData(
    val created_at: String,
    val datetime: Date,
    val id: Int,
    val invited_friends: List<UserData>,
    val latitude: Double,
    val longitude: Double,
    val place: String,
    val start_latitude: Double,
    val start_longitude: Double,
    val start_place: String,
    val title: String,
    val user: UserData,
    val user_id: Int
)