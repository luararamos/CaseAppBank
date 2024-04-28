package com.example.itautransferapp.data

import com.example.itautransferapp.data.model.UserAccount
import com.example.itautransferapp.data.model.UserAccountItem
import retrofit2.Call
import retrofit2.http.GET

interface UserAccountService {

    @GET("user")
    fun getUserAccount(): Call<UserAccount>
}