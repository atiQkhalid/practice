package com.example.practice.di

import android.content.Context
import com.example.practice.repository.ItemRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * DIFramework.kt
 * The dependency injection framework used by the app.
 * uses Koin for DI.
 */
object DIFramework {

    fun init(context: Context) {
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            val repoModule = module {
                single { ItemRepository.getInstance() }
            }
            // declare modules
            modules(repoModule)
        }
    }
}