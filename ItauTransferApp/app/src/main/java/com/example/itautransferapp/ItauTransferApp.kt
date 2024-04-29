package com.example.itautransferapp

import android.app.Application
import com.example.itautransferapp.domain.repository.di.repositoryModule
import com.example.itautransferapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ItauTransferApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ItauTransferApp)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}