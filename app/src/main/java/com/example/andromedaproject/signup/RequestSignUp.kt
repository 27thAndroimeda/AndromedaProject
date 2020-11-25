package com.example.andromedaproject.signup

import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSignUp{
    @POST("/users/signup")
    fun requestSignUp(@Body body: UserModel): retrofit2.Call<ResponseSignUp>
}