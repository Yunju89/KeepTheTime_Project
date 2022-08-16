package com.example.keepthetime_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepthetime_project.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setEvents() {

    }

    override fun setValues() {

    }
}