package com.example.keepthetime_project

import android.os.Bundle
import com.example.keepthetime_project.databinding.ActivitySearchUserBinding

class SearchUserActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setEvents()
        setValues()

    }

    override fun setEvents() {

    }

    override fun setValues() {

    }
}