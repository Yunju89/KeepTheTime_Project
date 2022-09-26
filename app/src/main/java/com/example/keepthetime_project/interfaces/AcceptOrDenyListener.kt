package com.example.keepthetime_project.interfaces

interface AcceptOrDenyListener {
    fun AcceptFriend(id : Int, type: String)

    fun DenyFriend(id : Int, type: String)
}