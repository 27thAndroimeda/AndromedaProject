package com.example.andromedaproject.signup

data class ResponseSignUp(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: UserModel
)