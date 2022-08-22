package com.example.keepthetime_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.keepthetime_project.databinding.ActivityManageMyFriendsBinding

class ManageMyFriendsActivity : BaseActivity() {

    private lateinit var binding : ActivityManageMyFriendsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageMyFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEvents()
        setValues()

    }

    override fun setEvents() {

    }

    override fun setValues() {

    }
}