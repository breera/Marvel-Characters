package com.breera.character_feature.di

import com.breera.character_feature.data.remote.CharactersRepositoryImpl
import com.breera.character_feature.data.remote.DefaultRepository
import com.breera.character_feature.data.remote.DefaultRepositoryImpl
import com.breera.character_feature.domain.CharactersRepository
import com.breera.character_feature.domain.GetCharactersUseCase
import com.breera.character_feature.domain.GetSectionInfoUseCase
import com.breera.character_feature.presentation.detail.DetailVM
import com.breera.character_feature.presentation.home.HomeVM
import com.breera.character_feature.presentation.pager.PagerViewVM
import com.breera.character_feature.presentation.shareddata.ShareViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Breera Hanif on 05/01/2025.
 */

val homeModule = module {
    singleOf(::CharactersRepositoryImpl).bind<CharactersRepository>()
    singleOf(::DefaultRepositoryImpl).bind<DefaultRepository>()
    single { GetCharactersUseCase(get()) }
    single { GetSectionInfoUseCase(get()) }
    single { HomeVM(get()) }
    single { ShareViewModel() }
    single { DetailVM(get()) }
    single { PagerViewVM(get()) }
}