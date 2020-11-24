package com.example.andromedaproject.signup

import retrofit2.http.Body
import retrofit2.http.POST

interface InterfaceSignUp{
    @POST("/users/signup")
    fun interfaceSignUp(@Body body: UserModel): retrofit2.Call<ResponseSignUp>
}