package com.example.itautransferapp.presentation.di

import com.example.itautransferapp.presentation.screens.home.HomeViewModel
import com.example.itautransferapp.presentation.screens.listStatement.ListBankStatementViewModel
import com.example.itautransferapp.presentation.screens.login.LoginViewModel
import com.example.itautransferapp.presentation.screens.transfer.ConfirmTransferViewModel
import com.example.itautransferapp.presentation.screens.transfer.TransferViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(androidContext(), get()) }
    viewModel { HomeViewModel(androidContext(), get()) }
    viewModel { ListBankStatementViewModel(androidContext(), get()) }
    viewModel { TransferViewModel() }
    viewModel { ConfirmTransferViewModel() }
}