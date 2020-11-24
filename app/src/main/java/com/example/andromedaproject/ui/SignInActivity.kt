package com.example.andromedaproject.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.andromedaproject.R
import com.example.andromedaproject.signin.RequestSignIn
import com.example.andromedaproject.signin.ResponseSignIn
import com.example.andromedaproject.signin.SignInModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class SignInActivity : BaseActivity() {
    /*
자동로그인 구현을 위해 SharedPreferences와 Editor 인터페이스 사용. 데이터를 key, value의 쌍으로 저장할 수 있다.
 */
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var executor: Executor
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        passwordLogIn()
        gotoSignUp()
        checkBiometricAvailable()
        generateBiometricDialog()
        authenticateBioMetrics()
        signInForBiometric()
    }

    private fun passwordLogIn() {
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        editor = preferences.edit()
        autoLogin()

        button_signin.setOnClickListener {
            if (!(edittext_id_signin.text.isNullOrBlank() || edittext_password_signin.text.isNullOrBlank())) {
                restfulSignIn()
            }
        }
    }

    private fun restfulSignIn() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://15.164.83.210:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RequestSignIn::class.java).requestSignIn(
            SignInModel(
                edittext_id_signin.text.toString(),
                edittext_password_signin.text.toString()
            )
        ).enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.success) {
                        goToMainActivity()
                        Log.d("data", response.body()!!.data.toString())
                    }
                } else {
                    Toast.makeText(this@SignInActivity, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("retrofit error:", "$t")
            }
        })
    }

    private fun autoLogin() {
        if (preferences.getBoolean("checkAutoLogIn", false)) {
            val email = preferences.getString("email", null).toString()
            Toast.makeText(this, "${email}님 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_LOGIN)
        }
    }

    private fun goToMainActivity() {
        checkAutoLogIn = true
        editor.putBoolean("checkAutoLogIn", checkAutoLogIn)
        editor.putString("email", edittext_id_signin.text.toString())
        editor.apply()
        startActivityForResult(
            Intent(this@SignInActivity, MainActivity::class.java),
            REQUEST_CODE_LOGIN
        )
    }

    private fun gotoSignUp() {
        textview_goto_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SIGNUP)
        }
    }

    private fun signInForBiometric() {
        button_biometrics.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    // 기기가 생체인증을 지원하는지 확인
    fun checkBiometricAvailable() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                Log.d("MY_APP_TAG", "App can authenticate using biometrics")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Log.d("MY_APP_TAG", "No biometric features available on this device")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Log.e("MY_APP_TAG", "필요한 인텐트 작업 호출 - 생략")
        }
    }

    fun authenticateBioMetrics() {
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(
            this@SignInActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@SignInActivity, "인증에러: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    Toast.makeText(this@SignInActivity, "인증 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    checkAutoLogIn = true
                    editor.putBoolean("checkAutoLogIn", checkAutoLogIn)
                    editor.apply()
                    startActivityForResult(intent, REQUEST_CODE_LOGIN)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })
    }

    fun generateBiometricDialog() {
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("PEACE Application")
            .setSubtitle("Biometric Authentication")
            .setNegativeButtonText("Sign In Using Password")
            .build()
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

    companion object {
        var checkAutoLogIn = false
        const val REQUEST_CODE_SIGNUP = 100
        const val REQUEST_CODE_LOGIN = 200
    }
}