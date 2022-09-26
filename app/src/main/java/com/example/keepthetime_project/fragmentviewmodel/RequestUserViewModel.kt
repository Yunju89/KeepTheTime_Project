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

class RequestUserViewModel : ViewModel() {

    private var _requestUserData = MutableLiveData<List<UserData>>()
    val requestUserData: LiveData<List<UserData>>
        get() = _requestUserData

    fun getRequestUserData(context: Context) {
        ServerAPI.apiList(context).getRequestFriendList("requested")
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _requestUserData.value = it.data?.friends
                        }
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }


    private var _acceptOrDeny = MutableLiveData<BasicResponse>()
    val acceptOrDeny : LiveData<BasicResponse>
        get() = _acceptOrDeny

    fun getRequestAcceptOrDeny(context: Context, id: Int, type: String){
        ServerAPI.apiList(context).putRequestAcceptOrDenyFriendRequest(id, type).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _acceptOrDeny.value = it
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}