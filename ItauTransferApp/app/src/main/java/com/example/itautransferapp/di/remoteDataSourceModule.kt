package com.example.itautransferapp.di

import android.app.Application
import com.example.itautransferapp.data.UserAccountRepositoryImpl
import com.example.itautransferapp.domain.repository.UserAccountRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { provideRepositoryUserAccount(get()) }
}

fun provideRepositoryUserAccount(context: Application): UserAccountRepository {
    return UserAccountRepositoryImpl(context)
}
