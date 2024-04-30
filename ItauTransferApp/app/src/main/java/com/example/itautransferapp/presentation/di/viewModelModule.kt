package com.example.itautransferapp.presentation.di

import com.example.itautransferapp.presentation.screens.home.HomeViewModel
import com.example.itautransferapp.presentation.screens.listStatement.ListBankStatementViewModel
import com.example.itautransferapp.presentation.screens.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(androidContext(), get()) }
    viewModel { HomeViewModel(androidContext(), get()) }
    viewModel { ListBankStatementViewModel(androidContext(), get()) }
}