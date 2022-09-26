package com.example.keepthetime_project.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.keepthetime_project.R
import com.example.keepthetime_project.adapters.RequestUserRecyclerAdapter
import com.example.keepthetime_project.databinding.FragmentMyFriendBinding
import com.example.keepthetime_project.databinding.FragmentMyProfileBinding
import com.example.keepthetime_project.databinding.FragmentRequestedUsersBinding
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.fragmentviewmodel.RequestUserViewModel


class RequestedUsersFragment : BaseFragment() {

    private var mBinding : FragmentRequestedUsersBinding? = null
    private val binding get() = mBinding!!
    private val requestUserViewModel by viewModels<RequestUserViewModel>()
    private val requestList = ArrayList<UserData>()

    lateinit var mRequestUserAdapter : RequestUserRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRequestedUsersBinding.inflate(inflater, container, false)
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

        requestUserViewModel.getRequestUserData(mContext)
        observer()

//        나에게 친구 요청한 사람 목록 > 리싸이클러뷰로 보여주기
        mRequestUserAdapter = RequestUserRecyclerAdapter(mContext, requestList)
        binding.requestUsersRecyclerView.adapter = mRequestUserAdapter

    }


    private fun observer(){
        requestUserViewModel.requestUserData.observe(viewLifecycleOwner, Observer {
            Log.d("yj", "요청친구목록 갱신 ${it.size}")
            requestList.clear()
            requestList.addAll(it)
            mRequestUserAdapter.notifyDataSetChanged()
        })
    }

}