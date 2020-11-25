package com.example.andromedaproject.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RequestWebSearch {
    @GET("/v2/search/web")
    fun requestWebSearch(@Header("Authorization") token: String, @Query("query") title: String): Call<WebSearchModel>
}