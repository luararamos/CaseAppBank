package com.example.itautransferapp.data.remote

import com.example.itautransferapp.data.remote.model.User
import com.example.itautransferapp.data.remote.model.UserAccount
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAccountService {
    @GET("user")
    fun getUserAccount(): Call<User>

    @GET("user/{id}/account")
    fun getUserAccountById(@Path("id") userId: String): Call<UserAccount>

}