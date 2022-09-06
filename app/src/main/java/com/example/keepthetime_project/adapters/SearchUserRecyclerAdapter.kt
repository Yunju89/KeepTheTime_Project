package com.example.keepthetime_project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_project.R
import com.example.keepthetime_project.api.ServerAPI
import com.example.keepthetime_project.datas.UserData

class SearchUserRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>
) : RecyclerView.Adapter<SearchUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val btnAddFriend = view.findViewById<Button>(R.id.btnAddFriend)

        fun bind(data : UserData){
            txtNickname.text = data.nick_name
            Glide.with(mContext).load(data.profile_img).into(imgProfile)

            when (data.provider){
                "default" -> {
                    txtEmail.text = data.email
                    Glide.with(mContext).load(data.profile_img).into(imgSocialLoginLogo)
                }
                "kakao" -> {
                    txtEmail.text = "카카오 로그인"
                    imgSocialLoginLogo.setImageResource(R.drawable.kakao_logo)
                }
                "naver" -> {
                    txtEmail.text = "네이버 로그인"
                    imgSocialLoginLogo.setImageResource(R.drawable.naver_logo)
                }
                "facebook" -> {
                    txtEmail.text = "페이스북 로그인"
                    imgSocialLoginLogo.setImageResource(R.drawable.facebook_logo)
                }
                else -> {

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.searched_user_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)

    }


    override fun getItemCount() = mList.size
}