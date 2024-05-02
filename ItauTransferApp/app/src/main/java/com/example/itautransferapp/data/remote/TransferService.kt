package com.example.itautransferapp.data.remote

import com.example.itautransferapp.data.remote.model.User
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.data.remote.model.UserAccountItem
import com.example.itautransferapp.data.remote.model.UserItem
import com.example.itautransferapp.data.remote.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TransferService {
    @GET("user/{id}/account")
    suspend fun getAccount(@Path("id") id: Int): List<UserAccountItem>

    @GET("user")
    suspend fun getUsers(): List<Users>

    @GET("user")
    fun getAllUsers(): List<UserAccount>

}