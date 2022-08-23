package com.example.keepthetime_project

import android.os.Bundle
import com.example.keepthetime_project.adapters.MainViewPagerAdapter
import com.example.keepthetime_project.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

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

        TabLayoutMediator(binding.tabLayout, binding.viewpager){ tab, position ->
            when(position){
                0 -> tab.text = "약속목록"
                1 -> tab.text = "내프로필"
            }
        }.attach()

    }
}