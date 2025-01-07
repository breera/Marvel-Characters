package com.breera.character_feature.di

import com.breera.character_feature.data.remote.CharactersRepositoryImpl
import com.breera.character_feature.domain.CharactersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Breera Hanif on 05/01/2025.
 */
val homeModule = module {
    singleOf(::CharactersRepositoryImpl).bind<CharactersRepository>()
}