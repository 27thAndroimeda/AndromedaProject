package com.example.andromedaproject.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.andromedaproject.R
import com.example.andromedaproject.network.RetrofitClient
import com.example.andromedaproject.signup.RequestSignUp
import com.example.andromedaproject.signup.ResponseSignUp
import com.example.andromedaproject.signup.UserModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : BaseActivity() {
    lateinit var retrofitClient: RequestSignUp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        completeSignUp()
    }

    private fun completeSignUp() {
        button_signup.setOnClickListener {

            val isInfoBlank = blankInfoCheck(
                edittext_name,
                edittext_id,
                edittext_password,
                edittext_check_password
            )
            val isValidatePassword = checkPassword(edittext_password, edittext_check_password)

            if (isInfoBlank and isValidatePassword) restfulSignUp()
        }
    }

    private fun blankInfoCheck(
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

    private fun checkPassword(password: EditText, passwordCheck: EditText): Boolean {
        if (password.text.toString().equals(passwordCheck.text.toString())) {
            return true
        } else {
            Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
    }


    private fun restfulSignUp() {
        retrofitClient = RetrofitClient.create(RequestSignUp::class.java)
        retrofitClient.requestSignUp(
            UserModel(
                edittext_id.text.toString(),
                edittext_password.text.toString(),
                edittext_name.text.toString()
            )
        ).enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.success) {
                        goToSignInActivity()
                        Log.d("status", response.body()!!.data.toString())
                    }
                }
            }
            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun goToSignInActivity() {
        val intent = Intent()
        intent.putExtra("email", edittext_id.text.toString())
        intent.putExtra("password", edittext_password.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}