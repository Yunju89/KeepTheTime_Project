package com.example.keepthetime_project.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.keepthetime_project.EditAppointmentActivity
import com.example.keepthetime_project.R
import com.example.keepthetime_project.adapters.MyAppointmentRecyclerAdapter
import com.example.keepthetime_project.databinding.ActivityEditAppointmentBinding
import com.example.keepthetime_project.databinding.FragmentAppointmentBinding
import com.example.keepthetime_project.datas.AppointmentsData
import com.example.keepthetime_project.fragmentviewmodel.MyAppointmentViewModel

class AppointmentFragment : BaseFragment() {

    private var mBinding : FragmentAppointmentBinding? = null
    private val binding get() = mBinding!!
    private val myAppointmentViewModel by viewModels<MyAppointmentViewModel>()
    private val appointmentList = ArrayList<AppointmentsData>()
    lateinit var adapter: MyAppointmentRecyclerAdapter

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
        observer()

    }

    override fun setEvents() {

        binding.btnAppointment.setOnClickListener {
            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
        myAppointmentViewModel.getMyAppointmentData(mContext)

        adapter = MyAppointmentRecyclerAdapter(mContext, appointmentList)
        binding.recyclerView.adapter = adapter

    }

    fun observer(){
        myAppointmentViewModel.myAppointment.observe(viewLifecycleOwner, Observer {
            Log.d("yj", "약속목록 불러오기 ${it.size}")
            appointmentList.clear()
            appointmentList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}