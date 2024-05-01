package com.example.itautransferapp.presentation.screens.listStatement

data class ListState<T>(
    val isLoading: Boolean = false,
    val value: List<T> = emptyList(),
    val error: String = ""
)