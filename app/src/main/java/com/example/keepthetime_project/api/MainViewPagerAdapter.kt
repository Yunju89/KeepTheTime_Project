package com.example.keepthetime_project.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.keepthetime_project.fragments.AppointmentFragment
import com.example.keepthetime_project.fragments.MyProfileFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AppointmentFragment()
            else -> MyProfileFragment()
        }
    }



}