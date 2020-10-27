package com.example.andromedaproject.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    /*
자동로그인 구현을 위해 SharedPreferences와 Editor 인터페이스 사용. 데이터를 key, value의 쌍으로 저장할 수 있다.
 */
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    val REQUEST_CODE_SIGNUP = 100
    val REQUEST_CODE_LOGIN = 200
    var checkAutoLogIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        logIn()
        gotoSignUp()
    }

    fun logIn() {
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        editor = pref.edit()

        btn_login.setOnClickListener {
            if (!(et_email_login.text.isNullOrBlank() || et_password_login.text.isNullOrBlank())) {
                //데이터를 저장할 때는 입력될 값의 타입에 맞는 Editor를 사용해서 저장할 수 있다.
                checkAutoLogIn = true
                editor.putBoolean("checkAutoLogIn", checkAutoLogIn)
                editor.putString("email", et_email_login.text.toString())
                //데이터를 저장하고 꼭! apply() 메서드를 호출해주어야 실제 파일에 반영됩니다.
                editor.apply()
                startActivityForResult(Intent(this, MainActivity::class.java), REQUEST_CODE_LOGIN)
            }
        }
        autoLogin()
    }

    fun autoLogin() {

        //값을 불러올 때는 타입에 맞는 get메서드를 사용한다.
        //괄호안에는 key와, 기본값을 넣는다
        if (pref.getBoolean("checkAutoLogIn", false) == true || !pref.getString("email", null)
                .isNullOrBlank()
        ) {
            val email = pref.getString("email", null).toString()
            Toast.makeText(this, "${email}님 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LOGIN)

        }
    }

    fun gotoSignUp() {
        tv_goto_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SIGNUP)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_SIGNUP -> {
                    et_email_login.setText(data?.getStringExtra("email").toString())
                    et_password_login.setText(data?.getStringExtra("password").toString())
                }
                REQUEST_CODE_LOGIN -> {
                    finish()
                }
            }
        }
    }
}