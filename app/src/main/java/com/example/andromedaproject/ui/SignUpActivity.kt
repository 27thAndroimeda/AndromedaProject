package com.example.andromedaproject.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.andromedaproject.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        completeSignUp()
    }

    fun completeSignUp() {
        button_signup.setOnClickListener {

            val isInfoBlank = blankInfoCheck(
                edittext_name,
                edittext_id,
                edittext_password,
                edittext_check_password
            )
            val isValidatePassword = checkPassword(edittext_password, edittext_check_password)

            if (isInfoBlank and isValidatePassword) goToSignInActivity()

        }
    }

    fun blankInfoCheck(
        name: EditText,
        id: EditText,
        password: EditText,
        passwordCheck: EditText
    ): Boolean {
        if (name.text.isNullOrBlank() or id.text.isNullOrBlank()
            or password.text.isNullOrBlank() or passwordCheck.text.isNullOrBlank()
        ) {
            Toast.makeText(this, "필수입력사항을 확인해주세요", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    fun checkPassword(password: EditText, passwordCheck: EditText): Boolean {
        if (password.text.toString().equals(passwordCheck.text.toString())) {
            return true
        } else {
            Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
    }


    fun goToSignInActivity() {
        val intent = Intent()
        intent.putExtra("email", edittext_id.text.toString())
        intent.putExtra("password", edittext_password.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}