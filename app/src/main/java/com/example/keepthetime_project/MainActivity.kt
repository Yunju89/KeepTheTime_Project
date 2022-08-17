package com.example.keepthetime_project

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.keepthetime_project.api.MainViewPagerAdapter
import com.example.keepthetime_project.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter : MainViewPagerAdapter

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

        mAdapter = MainViewPagerAdapter(this)
        binding.viewpager.adapter = mAdapter


    }
}