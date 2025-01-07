package com.breera.core.di

import com.breera.core.data.remote.HttpClientFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientFactory.create(OkHttp.create()) }
}