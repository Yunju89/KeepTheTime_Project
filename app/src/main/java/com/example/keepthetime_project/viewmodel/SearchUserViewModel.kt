package com.example.keepthetime_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import org.json.JSONObject
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

    private var _addFriend = MutableLiveData<BasicResponse>()
    val addFriend: LiveData<BasicResponse>
        get() = _addFriend

    fun callAddFriend(context: Context, id: Int) {
        Log.d("yj", "친구추가요청 API콜")
        ServerAPI.apiList(context).postRequestAddFriend(id)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _addFriend.value = it
                            Log.d("yj", "${it.message}")
                        }
                    } else{
                        response.errorBody()?.let {
                            val jsonObj = JSONObject(it.string())
                            val code = jsonObj.getInt("code")
                            val message = jsonObj.getString("message")

                            val basicResponse = BasicResponse(code, message)
                            _addFriend.value = basicResponse
                        }
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }
}