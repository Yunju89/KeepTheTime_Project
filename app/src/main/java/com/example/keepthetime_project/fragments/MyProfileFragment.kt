package com.example.keepthetime_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keepthetime_project.R
import com.example.keepthetime_project.databinding.FragmentAppointmentBinding
import com.example.keepthetime_project.databinding.FragmentMyProfileBinding

class MyProfileFragment : BaseFragment() {

    private var mBinding : FragmentMyProfileBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMyProfileBinding.inflate(inflater, container, false)
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

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}