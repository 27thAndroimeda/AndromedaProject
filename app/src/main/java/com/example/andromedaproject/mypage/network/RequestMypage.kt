package com.example.andromedaproject.mypage.network

import com.example.andromedaproject.mypage.model.Repository
import retrofit2.Call
import retrofit2.http.GET

interface RequestMypage {
    @GET("users/Kotlin/repos")
    fun getFriendsList(): Call<List<Repository>>
}
