package com.example.keepthetime_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
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
        val imgSocialLoginLogo = row.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val nickName = row.findViewById<TextView>(R.id.txtNickname)
        val email = row.findViewById<TextView>(R.id.txtEmail)


        Glide.with(mContext).load(data.profile_img).into(imgProfile)
        nickName.text = data.nick_name

        when (data.provider){
            "default" -> {
                email.text = data.email
                Glide.with(mContext).load(data.profile_img).into(imgSocialLoginLogo)
            }
            "kakao" -> {
                email.text = "카카오 로그인"
                Glide.with(mContext).load(R.drawable.kakao_logo).into(imgSocialLoginLogo)
            }
            "naver" -> {
                email.text = "네이버 로그인"
                Glide.with(mContext).load(R.drawable.naver_logo).into(imgSocialLoginLogo)
            }
            "facebook" -> {
                email.text = "페이스북 로그인"
                Glide.with(mContext).load(R.drawable.facebook_logo).into(imgSocialLoginLogo)
            }
            else -> {

            }
        }

        return row

    }


}