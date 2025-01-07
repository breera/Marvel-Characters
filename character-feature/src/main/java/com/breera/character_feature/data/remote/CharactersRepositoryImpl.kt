package com.breera.character_feature.data.remote

 import com.breera.character_feature.domain.CharactersRepository
 import com.breera.character_feature.domain.model.CharactersModel

/**
 * Created by Breera Hanif on 05/01/2025.
 */
class CharactersRepositoryImpl: CharactersRepository {
    override suspend fun getCharacters(): CharactersModel {
        // to do remote data fetch
         return CharactersModel()
    }
}