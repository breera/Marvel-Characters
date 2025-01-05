package com.breera.marvelcharactersapp

import android.app.Application
import com.breera.marvelcharactersapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

/**
 * Created by Breera Hanif on 05/01/2025.
 */
class MarvelCharacterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Koin for dependency injection
        initKoin {
            // Set up Android-specific logging for Koin
            androidLogger()
            // Provide the application context to Koin
            androidContext(this@MarvelCharacterApplication)
        }
    }
}