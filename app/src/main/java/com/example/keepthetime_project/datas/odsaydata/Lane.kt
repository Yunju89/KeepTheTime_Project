package com.example.keepthetime_project.datas.odsaydata

data class Lane(
    val busCityCode: Int,
    val busID: Int,
    val busLocalBlID: String,
    val busNo: String,
    val busProviderCode: Int,
    val name: String,
    val subwayCityCode: Int,
    val subwayCode: Int,
    val type: Int
)