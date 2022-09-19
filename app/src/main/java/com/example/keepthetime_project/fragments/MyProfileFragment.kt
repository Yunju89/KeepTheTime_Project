package com.example.keepthetime_project.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.keepthetime_project.ManageMyFriendsActivity
import com.example.keepthetime_project.SignInActivity
import com.example.keepthetime_project.databinding.FragmentMyProfileBinding
import com.example.keepthetime_project.fragmentviewmodel.MyProfileViewModel
import com.example.keepthetime_project.utils.ContextUtil

class MyProfileFragment : BaseFragment() {

    private var mBinding: FragmentMyProfileBinding? = null
    private val binding get() = mBinding!!
    private val myProfileViewModel by viewModels<MyProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setValues()
        setEvents()

    }

    override fun setEvents() {

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(mContext)
                .setTitle("로그아웃")
                .setMessage("정말 로그아웃 하시겠습니까?")
                .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                    ContextUtil.setLoginUserToken(mContext, "")
                    val myIntent = Intent(mContext, SignInActivity::class.java)
                    myIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(myIntent)


                })
                .setNegativeButton("아니요", null)
                .show()
        }


        binding.btnManageMyFriends.setOnClickListener {
            val myIntent = Intent(mContext, ManageMyFriendsActivity::class.java)
            startActivity(myIntent)
        }

    }


    override fun setValues() {

        myProfileViewModel.getMyInfo(mContext)
        observer()
    }

    private fun observer() {
        myProfileViewModel.myProfile.observe(viewLifecycleOwner, Observer {
            binding.txtNickname.text = it.nick_name
            Glide.with(mContext).load(it.profile_img).into(binding.imgProfile)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}