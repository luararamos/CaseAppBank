package com.example.itautransferapp.domain.repository.di

import com.example.itautransferapp.data.remote.UserRepositoryImpl
import com.example.itautransferapp.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(androidContext()) }
}