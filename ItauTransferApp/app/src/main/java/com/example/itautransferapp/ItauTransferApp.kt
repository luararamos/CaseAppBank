package com.example.itautransferapp

import android.app.Application
import com.example.itautransferapp.di.remoteDataSourceModule
import com.example.itautransferapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ItauTransferApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ItauTransferApp)
            modules(remoteDataSourceModule, viewModelModule)
        }
    }
}