package com.example.keepthetime_project

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.keepthetime_project.adapters.MainViewPagerAdapter
import com.example.keepthetime_project.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()


    }

    override fun setEvents() {
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.myAppointment -> binding.viewpager.currentItem = 0
                R.id.myProfile -> binding.viewpager.currentItem = 1
            }
            return@setOnItemSelectedListener true
        }

//        이벤트 처리 함수 직접 오버라이딩 필요
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.bottomNav.selectedItemId = when(position){
                    0 -> R.id.myAppointment
                    else -> R.id.myProfile
                }
            }
        })
    }

    override fun setValues() {

        binding.viewpager.adapter = MainViewPagerAdapter(this)// Activity 객체 -> Context 대입 불가


//        탭 레이아웃 / 뷰페이저 2
//        TabLayoutMediator(binding.tabLayout, binding.viewpager){ tab, position ->
//            when(position){
//                0 -> tab.text = "약속목록"
//                1 -> tab.text = "내프로필"
//            }
//        }.attach()

    }
}