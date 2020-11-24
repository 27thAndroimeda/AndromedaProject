package com.example.andromedaproject.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andromedaproject.R
import com.example.andromedaproject.ui.MainActivity
import com.example.andromedaproject.ui.VoiceRecognitionActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_test_recognition.setOnClickListener {
            startActivity(Intent((context as MainActivity), VoiceRecognitionActivity::class.java))
        }
    }
}