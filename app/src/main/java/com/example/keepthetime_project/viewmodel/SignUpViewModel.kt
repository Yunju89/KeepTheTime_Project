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
    private var _sighUpSuccess = MutableLiveData<BasicResponse>()
    val sighUpSuccess: LiveData<BasicResponse>
        get() = _sighUpSuccess


    fun getSighUp(context: Context, inputEmail: String, inputPw: String, inputNickName: String) {

        ServerAPI.apiList(context).putRequestSignup(inputEmail, inputPw, inputNickName)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            _sighUpSuccess.value = it
                        }

                    } else {
                        response.errorBody()?.let {
                            val jsonObj = JSONObject(it.string())
                            val message = jsonObj.getString("message")
                            val basicResponse = BasicResponse(message = message)

                            _sighUpSuccess.value = basicResponse

                        }
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }


    private var _emailCheck = MutableLiveData<BasicResponse>()
    val emailCheck: LiveData<BasicResponse>
        get() = _emailCheck

    fun getEmailCheck(context: Context, inputEmail: String) {
        ServerAPI.apiList(context).getRequestUserCheck("EMAIL", inputEmail)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _emailCheck.value = it
                        }
                    } else {
                        response.errorBody()?.let {
                            val jsonObj = JSONObject(it.string())
                            val message = jsonObj.getString("message")
                            val basicResponse = BasicResponse(message = message)
                            _emailCheck.value = basicResponse

                        }
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }


}