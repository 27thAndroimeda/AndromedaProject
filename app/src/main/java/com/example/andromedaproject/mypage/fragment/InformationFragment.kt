package com.example.andromedaproject.mypage.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andromedaproject.R
import com.example.andromedaproject.ui.SignInActivity
import com.example.andromedaproject.ui.SignInActivity.Companion.checkAutoLogIn
import com.example.andromedaproject.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_inforamation.*


class InformationFragment : Fragment() {
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inforamation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signOut()
    }

    private fun signOut() {
        button_sign_out.setOnClickListener {
            pref = (activity as MainActivity).getSharedPreferences("pref", Context.MODE_PRIVATE)
            editor = pref.edit()

            val intent = Intent(context, SignInActivity::class.java)

            checkAutoLogIn = false
            editor.putBoolean("checkAutoLogIn", checkAutoLogIn)
            editor.putString("email", null)
            editor.apply()

            startActivity(intent)
            (activity as MainActivity).finish()
        }
    }
}