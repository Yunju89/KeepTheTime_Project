package com.example.keepthetime_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_project.R
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.AppointmentsData
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.interfaces.AddFriendListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MyAppointmentRecyclerAdapter(
    val mContext : Context,
    val mList : List<AppointmentsData>,
) : RecyclerView.Adapter<MyAppointmentRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtDate = view.findViewById<TextView>(R.id.txtDate)
        val txtPlace = view.findViewById<TextView>(R.id.txtPlace)
        val imgViewMap = view.findViewById<ImageView>(R.id.imgViewMap)

        fun bind(data : AppointmentsData){

            txtTitle.text = data.title
            txtPlace.text = data.place

            val simpleDate = SimpleDateFormat("yy년 M월 d일 a h시 m분")
            txtDate.text = simpleDate.format(data.datetime)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)

    }


    override fun getItemCount() = mList.size
}