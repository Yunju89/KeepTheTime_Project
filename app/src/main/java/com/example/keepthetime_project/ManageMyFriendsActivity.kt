package com.example.keepthetime_project

import android.os.Bundle
import com.example.keepthetime_project.adapters.MyFriendAdapter
import com.example.keepthetime_project.databinding.ActivityManageMyFriendsBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendsActivity : BaseActivity() {

    private lateinit var binding : ActivityManageMyFriendsBinding

    val myFriendList = ArrayList<UserData>()

    lateinit var mAdapter : MyFriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageMyFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {

    }

    override fun setValues() {
        getMyFriendListFromServer()

        mAdapter = MyFriendAdapter(mContext, R.layout.my_friend_list_item, myFriendList)
        binding.myFriendsListView.adapter = mAdapter


    }

    fun getMyFriendListFromServer(){
        apiList.getRequestFriendList(ContextUtil.getLoginUserToken(mContext), "all").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        myFriendList.addAll(it.data.friends)

                        mAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }

}