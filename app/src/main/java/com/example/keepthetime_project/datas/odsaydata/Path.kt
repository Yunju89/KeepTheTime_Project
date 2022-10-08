package com.example.keepthetime_project.datas.odsaydata

data class Path(
    val info: Info,
    val pathType: Int,
    val subPath: List<SubPath>
)