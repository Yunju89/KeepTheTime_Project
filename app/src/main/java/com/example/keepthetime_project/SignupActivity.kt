package com.example.keepthetime_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepthetime_project.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}