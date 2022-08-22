package com.example.keepthetime_project.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.keepthetime_project.api.APIList
import com.example.keepthetime_project.api.ServerAPI

abstract class BaseFragment : Fragment(){

    lateinit var mContext : Context

    lateinit var apiList: APIList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI.getRetrofit() // = retrofit 객체 자체
        apiList = retrofit.create(APIList::class.java)


    }

    abstract fun setEvents()

    abstract fun setValues()

}