package com.example.itautransferapp.data.remote

import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.data.remote.model.UserAccountItem
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("user/{id}/account")
    suspend fun getAccount(@Path("id") id: Int): List<UserAccountItem>
}