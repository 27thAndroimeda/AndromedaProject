package com.example.andromedaproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_profile_detail.*

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        setUserDetailDescription()
    }

    fun setUserDetailDescription() {
        Glide.with(this).load(intent.getStringExtra("profileImageDetail"))
            .into(imageview_profile_detail)
        textview_name_detail.setText(intent.getStringExtra("userNameDetail"))
        textview_description_detail.setText(intent.getStringExtra("userDescriptionDetail"))
    }
}