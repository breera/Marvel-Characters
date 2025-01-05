package com.breera.marvelcharactersapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by Breera Hanif on 05/01/2025.
 */
fun initKoin(config : KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
    }
}