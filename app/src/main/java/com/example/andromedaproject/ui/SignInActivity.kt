package com.example.andromedaproject.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {
    /*
자동로그인 구현을 위해 SharedPreferences와 Editor 인터페이스 사용. 데이터를 key, value의 쌍으로 저장할 수 있다.
 */
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        logIn()
        gotoSignUp()
    }

    fun logIn() {
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        editor = pref.edit()
        autoLogin()

        button_signin.setOnClickListener {
            if (!(edittext_id_signin.text.isNullOrBlank() || edittext_password_signin.text.isNullOrBlank())) {
                checkAutoLogIn = true
                editor.putBoolean("checkAutoLogIn", checkAutoLogIn)
                editor.putString("email", edittext_id_signin.text.toString())
                editor.apply()
                startActivityForResult(Intent(this, MainActivity::class.java), REQUEST_CODE_LOGIN)
            }
        }
    }

    fun autoLogin() {
        if (pref.getBoolean("checkAutoLogIn", false) || !pref.getString("email", null)
                .isNullOrBlank()
        ) {
            val email = pref.getString("email", null).toString()
            Toast.makeText(this, "${email}님 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LOGIN)
        }
    }

    fun gotoSignUp() {
        textview_goto_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SIGNUP)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_SIGNUP -> {
                    edittext_id_signin.setText(data?.getStringExtra("email").toString())
                    edittext_password_signin.setText(data?.getStringExtra("password").toString())
                }
                REQUEST_CODE_LOGIN -> {
                    finish()
                }
            }
        }
    }

    companion object{
        var checkAutoLogIn = false
        const val REQUEST_CODE_SIGNUP = 100
        const val REQUEST_CODE_LOGIN = 200
    }
}