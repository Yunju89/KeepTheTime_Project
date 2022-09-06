package com.example.keepthetime_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.keepthetime_project.databinding.ActivitySplashBinding
import com.example.keepthetime_project.viewmodel.SplashViewModel

class SplashActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashBinding
    var isMyInfoLoaded = false
    lateinit var splashViewModel : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        setValues()
        setEvents()
    }

    override fun setEvents() {
        splashViewModel.isMyInfoLoaded.observe(this, Observer {
            isMyInfoLoaded = it
        })
    }

    override fun setValues() {
        splashViewModel.getMyInfo(this)


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