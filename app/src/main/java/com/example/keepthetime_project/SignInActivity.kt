package com.example.keepthetime_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepthetime_project.databinding.ActivityMainBinding
import com.example.keepthetime_project.databinding.ActivitySignInBinding

class SignInActivity : BaseActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {

    }

    override fun setValues() {

    }
}