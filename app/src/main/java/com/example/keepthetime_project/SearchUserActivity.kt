package com.example.keepthetime_project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.keepthetime_project.adapters.SearchUserRecyclerAdapter
import com.example.keepthetime_project.databinding.ActivitySearchUserBinding
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.viewmodel.SearchUserViewModel

class SearchUserActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchUserBinding

    private val mSearchUserList = ArrayList<UserData>()
    lateinit var mAdapter: SearchUserRecyclerAdapter
    private val searchUserViewModel by viewModels<SearchUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {

        binding.btnSearch.setOnClickListener {
            val inputNickName = binding.edtNickname.text.toString()
            searchUserViewModel.getUserList(mContext, inputNickName)

        }

    }

    override fun setValues() {
        observer()

        mAdapter = SearchUserRecyclerAdapter(mContext, mSearchUserList)
        binding.userListRecyclerView.adapter = mAdapter


    }

    private fun observer() {
        searchUserViewModel.userList.observe(this, Observer { basicResponse ->
            basicResponse.data?.let {
                mSearchUserList.addAll(it.users)
                mAdapter.notifyDataSetChanged()
            }
        })
    }

}