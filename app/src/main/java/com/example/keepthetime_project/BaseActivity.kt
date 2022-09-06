package com.example.keepthetime_project

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.keepthetime_project.api.APIList
import com.example.keepthetime_project.api.ServerAPI

abstract class BaseActivity() : AppCompatActivity() {

    lateinit var mContext : Context

    lateinit var apiList: APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)



    }

    abstract fun setEvents()

    abstract fun setValues()


}