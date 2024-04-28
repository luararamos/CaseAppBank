package com.example.itautransferapp.presentation

import androidx.compose.runtime.Composable
import com.example.itautransferapp.presentation.screens.home.HomeViewModel

@Composable
fun MyApp(viewModel: HomeViewModel) {
    val userData = viewModel.userData()
}