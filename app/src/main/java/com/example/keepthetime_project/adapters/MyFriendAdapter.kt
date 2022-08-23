package com.example.keepthetime_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.keepthetime_project.R
import com.example.keepthetime_project.datas.UserData

class MyFriendAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: ArrayList<UserData>
) : ArrayAdapter<UserData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item, null)
        }
        val row = tempRow!!

        val data = mList[position]

        val imgProfile = row.findViewById<ImageView>(R.id.imgProfile)
        val nickName = row.findViewById<TextView>(R.id.txtNickname)
        val email = row.findViewById<TextView>(R.id.txtEmail)

        Glide.with(mContext).load(data.profile_img).into(imgProfile)
        nickName.text = data.nick_name
        email.text = data.email


        return row

    }


}