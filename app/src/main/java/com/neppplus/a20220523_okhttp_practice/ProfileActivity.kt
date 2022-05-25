package com.neppplus.a20220523_okhttp_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.a20220523_okhttp_practice.databinding.ActivityProfileBinding
import com.neppplus.a20220523_okhttp_practice.utils.GlobalData

class ProfileActivity : BaseActivity() {

    lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        binding.currentNickTxt.text = GlobalData.loginUser!!.nickname
    }
}