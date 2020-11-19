package com.example.andromedaproject.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_profile_detail.*
import java.util.*

class ProfileDetailActivity : AppCompatActivity() {
    private val textToSpeechEngine: TextToSpeech by lazy {
        // Pass in context and the listener.
        TextToSpeech(this,
            TextToSpeech.OnInitListener { status ->
                // set our locale only if init was success.
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeechEngine.language = Locale.UK
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        setUserDetailDescription()
    }

    private fun setUserDetailDescription() {
        Glide.with(this).load(intent.getStringExtra("profileImageDetail"))
            .into(imageview_profile_detail)
        textview_name_detail.setText(intent.getStringExtra("userNameDetail"))
        textview_description_detail.setText(intent.getStringExtra("userDescriptionDetail"))

    }
}