package com.example.keepthetime_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keepthetime_project.R
import com.example.keepthetime_project.datas.UserData

class SearchUserRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>
) : RecyclerView.Adapter<SearchUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.searched_user_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }


    override fun getItemCount() = mList.size
}