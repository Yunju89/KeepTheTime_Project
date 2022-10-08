package com.example.keepthetime_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.odsaydata.Result
import com.google.gson.Gson
import com.naver.maps.geometry.LatLng
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAppointmentViewModel : ViewModel() {

    private var _appointment = MutableLiveData<BasicResponse>()
    val appointment: LiveData<BasicResponse>
        get() = _appointment

    fun postAddAppointment(
        context: Context,
        title: String,
        datetime: String,
        place: String,
        latitude: Double,
        longitude: Double
    ) {
        ServerAPI.apiList(context)
            .postRequestAddAppointment(title, datetime, place, latitude, longitude)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _appointment.value = it
                        }
                    } else {
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


    private var _odsayData = MutableLiveData<Result>()
    val odsayData: LiveData<Result>
        get() = _odsayData

    fun getODSayResult(context: Context, sX: String, sY: String, eX: String, eY: String) {
        val myODSayService =
            ODsayService.init(context, "qiVt8z/WkU8uqSBTpRQe+DllZJUeVZiTYUrk+h10pz8")

        myODSayService.requestSearchPubTransPath(
            sX, sY, eX, eY, "0", null, "0", object : OnResultCallbackListener {
                override fun onSuccess(p0: ODsayData?, p1: API?) {
                    p0?.let { oDsayData ->
                        val odsayObj = oDsayData.json.getJSONObject("result")
                        val data = Gson().fromJson(odsayObj.toString(), Result::class.java)

                        _odsayData.value = data

                    }
                }

                override fun onError(p0: Int, p1: String?, p2: API?) {
                }

            })

    }

}