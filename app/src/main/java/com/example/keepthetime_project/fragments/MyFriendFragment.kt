package com.example.keepthetime_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keepthetime_project.adapters.MyFriendRecyclerAdapter
import com.example.keepthetime_project.databinding.FragmentMyFriendBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyFriendFragment : BaseFragment() {

    private var mBinding: FragmentMyFriendBinding? = null
    private val binding get() = mBinding!!

    val myFriendList = ArrayList<UserData>()

    lateinit var mFriendAdapter : MyFriendRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMyFriendBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEvents()
        setValues()
    }

    override fun setEvents() {
    }

    override fun setValues() {
        getMyFriendsFromServer()

        mFriendAdapter = MyFriendRecyclerAdapter(mContext, myFriendList)
        binding.myFriendRecyclerView.adapter = mFriendAdapter
    }

    private fun getMyFriendsFromServer() {
        apiList.getRequestFriendList("my").enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        myFriendList.clear()
                        myFriendList.addAll(it.data.friends)

                        mFriendAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
            }

        })
    }


}