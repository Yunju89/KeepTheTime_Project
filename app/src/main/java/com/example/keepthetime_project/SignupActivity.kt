package com.example.keepthetime_project

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.keepthetime_project.databinding.ActivitySignupBinding
import com.example.keepthetime_project.viewmodel.SignUpViewModel

class SignupActivity : BaseActivity() {

    private lateinit var binding: ActivitySignupBinding
    lateinit var sighUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sighUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        setEvents()
        setValues()


    }

    override fun setEvents() {
        binding.btnSingUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            if (inputEmail.isEmpty()) {
                Toast.makeText(mContext, "이메일을 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (inputPw.isEmpty()) {
                Toast.makeText(mContext, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (inputNickname.isEmpty()) {
                Toast.makeText(mContext, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                sighUpViewModel.getSighUp(mContext, inputEmail, inputPw, inputNickname)
            }


        }

        binding.btnEmailCheck.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()

            if (inputEmail.isEmpty()) {
                Toast.makeText(mContext, "이메일을 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                sighUpViewModel.getEmailCheck(mContext, inputEmail)
            }


        }

    }

    override fun setValues() {
        observe()
    }

    private fun observe() {
        sighUpViewModel.sighUpSuccess.observe(this, Observer {

            if (it.code == 200) {
                Toast.makeText(mContext, it.message, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(mContext, it.message, Toast.LENGTH_SHORT).show()
            }
            Log.d("yj", "sighUp : ${it.message}")
        })


        sighUpViewModel.emailCheck.observe(this, Observer {

            Toast.makeText(mContext, it.message, Toast.LENGTH_SHORT).show()
            Log.d("yj", "emailCheck : ${it.message}")

        })

    }


}