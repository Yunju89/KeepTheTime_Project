package com.example.keepthetime_project.fragmentviewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileViewModel : ViewModel() {

    private val _myProfile = MutableLiveData<UserData>()
    val myProfile: LiveData<UserData>
        get() = _myProfile


    fun getMyInfo(context: Context) {
        ServerAPI.apiList(context).getRequestMyInfo().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _myProfile.value = it.data?.user
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}