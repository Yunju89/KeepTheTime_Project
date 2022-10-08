package com.example.keepthetime_project.datas.odsaydata

data class Info(
    val busStationCount: Int,
    val busTransitCount: Int,
    val firstStartStation: String,
    val lastEndStation: String,
    val mapObj: String,
    val payment: Int,
    val subwayStationCount: Int,
    val subwayTransitCount: Int,
    val totalDistance: Int,
    val totalStationCount: Int,
    val totalTime: Int,
    val totalWalk: Int,
    val totalWalkTime: Int,
    val trafficDistance: Int
)