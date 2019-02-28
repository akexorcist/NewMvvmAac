package com.akexorcist.extralivedatatest.network

import com.akexorcist.extralivedatatest.vo.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/ake/awesome")
    fun getUser(): Call<User>
}