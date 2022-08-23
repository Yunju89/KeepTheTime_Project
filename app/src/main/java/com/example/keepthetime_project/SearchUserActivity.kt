package com.example.keepthetime_project

import android.os.Bundle
import com.example.keepthetime_project.adapters.SearchUserRecyclerAdapter
import com.example.keepthetime_project.databinding.ActivitySearchUserBinding
import com.example.keepthetime_project.datas.BasicResponse
import com.example.keepthetime_project.datas.UserData
import com.example.keepthetime_project.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchUserBinding

    val mSearchUserList = ArrayList<UserData>()
    lateinit var mAdapter : SearchUserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setEvents()
        setValues()

    }

    override fun setEvents() {

        binding.btnSearch.setOnClickListener {
            val inputNickName = binding.edtNickname.text.toString()

            apiList.getRequestSearchUser(ContextUtil.getLoginUserToken(mContext), inputNickName).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    mSearchUserList.clear()

                    if(response.isSuccessful){
                        response.body()?.let {
                            mSearchUserList.addAll(it.data.users)
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                }



            })


        }

    }

    override fun setValues() {

        mAdapter = SearchUserRecyclerAdapter(mContext, mSearchUserList)
        binding.userListRecyclerView.adapter = mAdapter

    }
}