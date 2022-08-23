package com.example.keepthetime_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.keepthetime_project.databinding.ActivitySplashBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValues()
        setEvents()
    }

    override fun setEvents() {

    }

    override fun setValues() {

        var isMyInfoLoaded = false

        apiList.getRequestMyInfo(ContextUtil.getLoginUserToken(mContext)).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    isMyInfoLoaded = true

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent : Intent = if (isMyInfoLoaded){
                Intent(mContext, MainActivity::class.java)
            }else{
                Intent(mContext, SignInActivity::class.java)
            }

            startActivity(myIntent)
            finish()

        }, 2500)

    }
}