package com.example.keepthetime_project.viewmodel

import android.content.Context
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

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage



    fun getSignIn(context: Context, inputEmail: String, inputPw: String) {
        _errorMessage.value = ""

        ServerAPI.apiList(context).postRequestLogin(inputEmail, inputPw)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _isSignIn.value = it
                            ContextUtil.setLoginUserToken(context, it.data.token)


                        }
                    } else {
                        response.errorBody()?.let {
                            val jsonObj = JSONObject(it.string())
                            val message = jsonObj.getString("message")

                            _errorMessage.value = message

                        }
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }

}