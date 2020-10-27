package com.example.andromedaproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_profile_detail.*

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        setUserDetailDescription()
    }

    fun setUserDetailDescription(){
        imageview_profile_detail.setImageResource(intent.getIntExtra("profileImageDetail",0))
        textview_name_detail.setText(intent.getStringExtra("userNameDetail"))
        textview_description_detail.setText(intent.getStringExtra("userDescriptionDetail"))
    }
}