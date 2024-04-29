package com.example.itautransferapp.data.remote

import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.data.remote.model.UserAccountItem
import retrofit2.Call
import retrofit2.http.GET

interface UserAccountService {

    @GET("user")
    fun getUserAccount(): Call<UserAccount>
}