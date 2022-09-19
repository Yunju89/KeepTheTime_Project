package com.example.keepthetime_project.fragmentviewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendViewModel : ViewModel() {

    private val _myFriendData = MutableLiveData<List<UserData>>()
    val myFriendData : LiveData<List<UserData>>
        get() = _myFriendData

    fun getMyFriendData(context: Context, type : String){

        ServerAPI.apiList(context).getRequestFriendList(type).enqueue(object :
            Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _myFriendData.value = it.data?.friends
                    }
                    Log.d("yj", "getFriendData : 성공")
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }

}