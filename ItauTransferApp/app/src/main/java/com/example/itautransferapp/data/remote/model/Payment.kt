package com.example.itautransferapp.data.remote.model

data class Payment(
    val amount: String,
    val category: String,
    val createdAt: String,
    val id: String,
    val name: String
)