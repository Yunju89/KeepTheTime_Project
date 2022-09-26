package com.example.keepthetime_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keepthetime_project.EditAppointmentActivity
import com.example.keepthetime_project.R
import com.example.keepthetime_project.databinding.ActivityEditAppointmentBinding
import com.example.keepthetime_project.databinding.FragmentAppointmentBinding

class AppointmentFragment : BaseFragment() {

    private var mBinding : FragmentAppointmentBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAppointmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEvents()
        setValues()

    }

    override fun setEvents() {

        binding.btnAppointment.setOnClickListener {
            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}