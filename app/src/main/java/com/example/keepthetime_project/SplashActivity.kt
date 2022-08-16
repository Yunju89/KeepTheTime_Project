package com.example.keepthetime_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.keepthetime_project.databinding.ActivitySplashBinding

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

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent = Intent(mContext, SignInActivity::class.java)
            startActivity(myIntent)
            finish()

        }, 2500)

    }
}