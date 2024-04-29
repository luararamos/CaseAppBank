package com.example.itautransferapp.domain.repository.di

import com.example.itautransferapp.data.remote.UserAccountRepositoryImpl
import com.example.itautransferapp.domain.repository.UserAccountRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<UserAccountRepository> { UserAccountRepositoryImpl(androidContext()) }
}