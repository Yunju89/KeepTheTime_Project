package com.example.keepthetime_project

import android.os.Bundle
import com.example.keepthetime_project.databinding.ActivityMainBinding

private lateinit var binding : ActivityMainBinding

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()


    }

    override fun setEvents() {

    }

    override fun setValues() {

    }
}