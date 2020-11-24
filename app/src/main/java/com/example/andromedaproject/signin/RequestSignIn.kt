package com.example.andromedaproject.signin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSignIn {
    @POST("/users/signin")
    fun requestSignIn(@Body body: SignInModel): Call<ResponseSignIn>
}