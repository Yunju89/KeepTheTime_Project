package com.example.keepthetime_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.keepthetime_project.adapters.MyFriendRecyclerAdapter
import com.example.keepthetime_project.databinding.FragmentMyFriendBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.fragmentviewmodel.MyFriendViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyFriendFragment : BaseFragment() {

    private var mBinding: FragmentMyFriendBinding? = null
    private val binding get() = mBinding!!
    private val myFriendList = ArrayList<UserData>()
    private val myFriendViewModel by viewModels<MyFriendViewModel>()

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
        myFriendViewModel.getMyFriendData(mContext, "my")
        observer()

        mFriendAdapter = MyFriendRecyclerAdapter(mContext, myFriendList)
        binding.myFriendRecyclerView.adapter = mFriendAdapter
    }

    private fun observer(){
        myFriendViewModel.myFriendData.observe(viewLifecycleOwner, Observer {
            myFriendList.clear()
            myFriendList.addAll(it)
            mFriendAdapter.notifyDataSetChanged()
        })
    }



}