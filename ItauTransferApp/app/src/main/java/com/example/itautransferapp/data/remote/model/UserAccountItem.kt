package com.example.itautransferapp.data.remote.model

data class UserAccountItem(
    val account: String,
    val amount: String,
    val createdAt: String,
    val id: String,
    val payments: List<Payment>,
    val userId: String
)