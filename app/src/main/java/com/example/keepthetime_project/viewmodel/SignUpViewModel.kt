package com.example.keepthetime_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private var _sighUpSuccess = MutableLiveData<Boolean>()
    val sighUpSuccess : LiveData<Boolean>
        get() = _sighUpSuccess


    init {
        _sighUpSuccess.value = false
    }

    fun getSighUp(context : Context, inputEmail:String, inputPw:String, inputNickName:String){

        ServerAPI.apiList(context).putRequestSignup(inputEmail, inputPw,inputNickName).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                _sighUpSuccess.value = response.isSuccessful
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }





}