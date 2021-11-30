package com.example.nagwa_task.base

import android.app.Application

class MyApp : Application() {

    companion object{
        lateinit var instance: MyApp
    }

    init {
        instance = this
    }
}