package com.example.keepthetime_project.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.APIList
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel : ViewModel() {
    private var _isMyInfoLoaded = MutableLiveData<Boolean>()
    val isMyInfoLoaded: LiveData<Boolean>
        get() = _isMyInfoLoaded

    //    기본 생성자 호출 직 후 바로 실행되는 코드 블럭. (가장 먼저 초기화 되는 부분)
    init {
        _isMyInfoLoaded.value = false
    }

    fun getMyInfo(context: Context) {

        ServerAPI.apiList().getRequestMyInfo(ContextUtil.getLoginUserToken(context))
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        _isMyInfoLoaded.value = true
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

    }


}