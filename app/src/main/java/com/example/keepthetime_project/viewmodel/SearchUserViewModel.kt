package com.example.keepthetime_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel : ViewModel() {

    private var _userList = MutableLiveData<BasicResponse>()
    val userList: LiveData<BasicResponse>
        get() = _userList

    fun getUserList(context: Context, inputNickName: String) {

        ServerAPI.apiList(context).getRequestSearchUser(inputNickName)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _userList.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }

}