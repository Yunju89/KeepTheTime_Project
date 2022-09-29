package com.example.keepthetime_project.fragmentviewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.AppointmentsData
import com.example.keepthetime_project.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAppointmentViewModel : ViewModel() {

    private var _myAppointment = MutableLiveData<List<AppointmentsData>>()
    val myAppointment: LiveData<List<AppointmentsData>>
        get() = _myAppointment

    fun getMyAppointmentData(context: Context) {
        ServerAPI.apiList(context).getRequestMyAppointment()
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _myAppointment.value = it.data?.appointments

                        }
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
    }


}