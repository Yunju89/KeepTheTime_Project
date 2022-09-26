package com.example.keepthetime_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_project.R
import com.example.keepthetime_project.datas.UserData

class MyFriendRecyclerAdapter(
    val mContext: Context,
    val mList : List<UserData>
) : RecyclerView.Adapter<MyFriendRecyclerAdapter.MyViewHolder>(){

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val nickName = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val email = view.findViewById<TextView>(R.id.txtEmail)

        fun bind(data: UserData){

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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size
}