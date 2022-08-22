package com.example.keepthetime_project.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.keepthetime_project.SignInActivity
import com.example.keepthetime_project.databinding.FragmentMyProfileBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.utils.ContextUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    private var mBinding : FragmentMyProfileBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setValues()
        setEvents()

    }

    override fun setEvents() {

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(mContext)
                .setTitle("로그아웃")
                .setMessage("정말 로그아웃 하시겠습니까?")
                .setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                    ContextUtil.setLoginUserToken(mContext, "")
                    val myIntent = Intent(mContext, SignInActivity::class.java)
                    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(myIntent)


                })
                .setNegativeButton("아니요", null)
                .show()
        }

    }

    override fun setValues() {

        Log.d("yj", "setvalues실행")

        apiList.getRequestMyInfo(ContextUtil.getLoginUserToken(mContext)).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        Log.d("yj", "LoginUser : ${it.data.user.nick_name}")

                        binding.txtNickname.text = it.data.user.nick_name
                        Glide.with(mContext).load(it.data.user.profile_img).into(binding.imgProfile)

                    }
                }
                else{
                    response.errorBody()?.let {
                        val jsonObj = JSONObject(it.string())
                        val message = jsonObj.getString("message")
                        Log.d("yj", "myInfo400 : $message")
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.d("yj", "responseFail : 실패")
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}