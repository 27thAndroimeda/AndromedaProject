package com.example.andromedaproject.signin

import com.example.andromedaproject.signup.UserModel

data class ResponseSignIn(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: UserModel
)