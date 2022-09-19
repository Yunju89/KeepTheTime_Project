package com.example.keepthetime_project

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.keepthetime_project.adapters.SearchUserRecyclerAdapter
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.databinding.ActivitySearchUserBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.interfaces.AddFriendListener
import com.example.keepthetime_project.viewmodel.SearchUserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserActivity : BaseActivity(), AddFriendListener {
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
        observer()
    }

    override fun setEvents() {

        binding.btnSearch.setOnClickListener {
            val inputNickName = binding.edtNickname.text.toString()
            searchUserViewModel.getUserList(mContext, inputNickName)

        }

    }

    override fun setValues() {

        mAdapter = SearchUserRecyclerAdapter(mContext, mSearchUserList, this)
        binding.userListRecyclerView.adapter = mAdapter


    }

    private fun observer() {
        searchUserViewModel.userList.observe(this, Observer { basicResponse ->
            basicResponse.data?.let {
                mSearchUserList.addAll(it.users)
                mAdapter.notifyDataSetChanged()
            }
        })
        
        searchUserViewModel.addFriend.observe(this, Observer { 
            if(it.code == 200){
                Toast.makeText(mContext, "친구 요청을 전송하였습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun btnAddFriend(id: Int) {
        searchUserViewModel.callAddFriend(mContext, id)

    }


}