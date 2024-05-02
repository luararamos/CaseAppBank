package com.example.itautransferapp.domain.repository

import com.example.itautransferapp.data.remote.model.UserAccountItem
import com.example.itautransferapp.data.remote.model.Users

interface TransferRepository {
    suspend fun checkAmount(transferValue: String): Boolean
    suspend fun getAmount(): String
    suspend fun getUsers(): List<Users>
    suspend fun getAccountResponses(id: Int): List<UserAccountItem>
    fun getUserId(): String
}