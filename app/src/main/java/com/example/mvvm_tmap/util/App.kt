package com.example.mvvm_tmap.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


@SuppressLint("Registered")
class App : Application(){
    companion object {
        lateinit var instance : App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun context(): Context = applicationContext
}