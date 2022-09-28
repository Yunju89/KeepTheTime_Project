package com.example.keepthetime_project

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import com.example.keepthetime_project.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    private lateinit var binding: ActivityEditAppointmentBinding

    //    약속 시간 저장하는 멤버변수, 현재시간 세팅
    val mSelectedAppointmentDateTime = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {
        binding.txtDate.setOnClickListener {
            val dateSet = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayofMonth: Int) {

                    mSelectedAppointmentDateTime.set(year, month, dayofMonth)
                    val simpleDate = SimpleDateFormat("yy/MM/dd")

                    binding.txtDate.text = simpleDate.format(mSelectedAppointmentDateTime.time)
                }
            }

            val datePickerDialog = DatePickerDialog(
                mContext,
                dateSet,
                mSelectedAppointmentDateTime.get(Calendar.YEAR),
                mSelectedAppointmentDateTime.get(Calendar.MONTH),
                mSelectedAppointmentDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }

    override fun setValues() {

    }
}