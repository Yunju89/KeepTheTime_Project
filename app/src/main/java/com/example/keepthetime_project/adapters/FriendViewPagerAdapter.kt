package com.example.keepthetime_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.keepthetime_project.fragments.AppointmentFragment
import com.example.keepthetime_project.fragments.MyFriendFragment
import com.example.keepthetime_project.fragments.MyProfileFragment
import com.example.keepthetime_project.fragments.RequestedUsersFragment

class FriendViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MyFriendFragment()
            else -> RequestedUsersFragment()
        }
    }



}