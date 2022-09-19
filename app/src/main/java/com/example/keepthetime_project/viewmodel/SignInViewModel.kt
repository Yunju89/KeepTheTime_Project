package com.example.keepthetime_project.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.utils.ContextUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {

    private var _isSignIn = MutableLiveData<BasicResponse>()
    val isSignIn: LiveData<BasicResponse>
        get() = _isSignIn


    fun getSignIn(context: Context, inputEmail: String, inputPw: String) {

        ServerAPI.apiList(context).postRequestLogin(inputEmail, inputPw)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _isSignIn.value = it
                            it.data?.let { it1 -> ContextUtil.setLoginUserToken(context, it1.token) }


                        }
                    } else {
                        response.errorBody()?.let {
                            val jsonObj = JSONObject(it.string())
                            val message = jsonObj.getString("message")
                            val basicResponse = BasicResponse(message = message)
                            _isSignIn.value = basicResponse

                        }
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }


}