package com.example.keepthetime_project

import android.content.Intent
import android.os.Bundle
import com.example.keepthetime_project.adapters.FriendViewPagerAdapter
import com.example.keepthetime_project.adapters.MyFriendAdapter
import com.example.keepthetime_project.databinding.ActivityManageMyFriendsBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.utils.ContextUtil
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendsActivity : BaseActivity() {

    private lateinit var binding : ActivityManageMyFriendsBinding

    lateinit var mAdapter : FriendViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageMyFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {

        binding.btnAddFriend.setOnClickListener {
            val myIntent = Intent(mContext, SearchUserActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

        mAdapter = FriendViewPagerAdapter(this)
        binding.friendViewPager.adapter = mAdapter

        TabLayoutMediator(binding.friendTabLayout, binding.friendViewPager){ tab, position ->
            when(position){
                0 -> tab.text = "내 친구 목록"
                1 -> tab.text = "친구 요청 확인"
            }
        }.attach()



    }

}