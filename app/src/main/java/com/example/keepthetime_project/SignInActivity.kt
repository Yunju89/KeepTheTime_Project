package com.example.keepthetime_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.keepthetime_project.databinding.ActivitySignInBinding
import com.example.keepthetime_project.viewmodel.SignInViewModel

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding
    private val sighInViewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {

        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPw.text.toString()
            sighInViewModel.getSignIn(this, inputEmail, inputPw)
        }


        binding.btnSignup.setOnClickListener {
            val myIntent = Intent(mContext, SignupActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
        observer()
    }


    private fun observer() {
        sighInViewModel.isSignIn.observe(this, Observer {

            if (it.code == 200) {
                Toast.makeText(this, "${it.data?.user?.nick_name}님, 환영합니다.", Toast.LENGTH_SHORT)
                    .show()
                goMain()

            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun goMain() {
        val myIntent = Intent(mContext, MainActivity::class.java)
        startActivity(myIntent)
        finish()
    }

}