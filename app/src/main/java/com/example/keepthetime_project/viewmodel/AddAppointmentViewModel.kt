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

class AddAppointmentViewModel : ViewModel(){

    private var _appointment = MutableLiveData<BasicResponse>()
    val appointment : LiveData<BasicResponse>
        get() = _appointment

    fun postAddAppointment(context: Context, title: String, datetime: String, place: String, latitude: Double, longitude: Double){
        ServerAPI.apiList(context).postRequestAddAppointment(title, datetime, place, latitude, longitude).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        _appointment.value = it
                    }
                }
                else{
                    response.errorBody()?.let {
                        val jsonObj = JSONObject(it.string())
                        val message = jsonObj.getString("message")
                        val code = jsonObj.getInt("code")

                        val basicResponse = BasicResponse(message = message, code = code)
                        _appointment.value = basicResponse
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}