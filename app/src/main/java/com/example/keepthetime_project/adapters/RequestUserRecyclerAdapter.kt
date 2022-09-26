package com.example.keepthetime_project.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_project.R
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.interfaces.AcceptOrDenyListener

class RequestUserRecyclerAdapter(
    val mContext: Context,
    val mList: List<UserData>,
    val listener : AcceptOrDenyListener
) : RecyclerView.Adapter<RequestUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val btnAccept = view.findViewById<Button>(R.id.btnAccept)
        val btnDeny = view.findViewById<Button>(R.id.btnDeny)

        fun bind(data: UserData) {

            txtNickname.text = data.nick_name
            Glide.with(mContext).load(data.profile_img).into(imgProfile)

            when (data.provider) {
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

//            tag 사용해서 클릭 구현하기
            val onClick = View.OnClickListener {
                val tagStr = it.tag.toString()
                Log.d("yj", "클릭 버튼 확인하기 : $tagStr")

                when (tagStr) {
                    "수락" -> {
                        listener.AcceptFriend(data.id, tagStr)
                    }
                    "거절" -> {
                        listener.DenyFriend(data.id, tagStr)
                    }
               }
            }

            btnAccept.setOnClickListener(onClick)
            btnDeny.setOnClickListener(onClick)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row =
            LayoutInflater.from(mContext).inflate(R.layout.requested_user_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount() = mList.size

}