package com.example.mvvm_tmap

import android.app.Application
import android.content.Context
import com.example.mvvm_tmap.di.myDiModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application(){
    companion object {
        lateinit var instance : MyApplication
            private set
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(applicationContext)
            modules(myDiModule)
        }
    }

    fun context(): Context = applicationContext
}