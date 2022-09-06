package com.example.keepthetime_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.keepthetime_project.databinding.ActivitySignInBinding
import com.example.keepthetime_project.viewmodel.SignInViewModel

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var sighInViewModel: SignInViewModel

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

            callSignIn(inputEmail, inputPw)
        }


        binding.btnSignup.setOnClickListener {
            val myIntent = Intent(mContext, SignupActivity::class.java)
            startActivity(myIntent)
        }


    }

    override fun setValues() {

    }


    private fun callSignIn(inputEmail: String, inputPw: String) {

        sighInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        sighInViewModel.getSignIn(this, inputEmail, inputPw)
        sighInViewModel.isSignIn.observe(this, Observer {

            if (it.code == 200) {
                Toast.makeText(this, "${it.data.user.nick_name}님, 환영합니다.", Toast.LENGTH_SHORT)
                    .show()

                goMain()
                finish()
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }

            Log.d("yj", "code : ${it.code} , message : ${it.message}")

        })

        sighInViewModel.errorMessage.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                Log.d("yj", "에러메시지실행")
            }
        })

    }

    private fun goMain() {
        val myIntent = Intent(mContext, MainActivity::class.java)
        startActivity(myIntent)
        finish()
    }

}