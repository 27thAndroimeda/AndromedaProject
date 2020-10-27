package com.example.andromedaproject.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        completeSignUp()
    }

    fun completeSignUp() {
        btn_signup.setOnClickListener {

            if (et_name.text.isNullOrBlank() || et_email.text.isNullOrBlank() || et_password.text.isNullOrBlank()) {
                Toast.makeText(this, "필수입력사항을 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                if (et_password.text.toString().equals(et_check_password.text.toString())) {
                    goToLoginActivity()
                } else {
                    Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun goToLoginActivity() {
        val intent = Intent()
        intent.putExtra("email", et_email.text)
        intent.putExtra("password", et_password.text)
        setResult(Activity.RESULT_OK)
        finish()
    }
}