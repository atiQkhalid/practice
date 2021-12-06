package com.example.practice

import android.app.Application
import android.content.Context
import com.example.practice.di.DIFramework

/**
 * The App.kt, Application class
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        com.example.practice.App.Companion.instance = this
        DIFramework.init(this)
    }

    companion object {
        var instance: com.example.practice.App? = null
        fun getAppContext(): Context {
            return com.example.practice.App.Companion.instance as Context
        }
    }
}