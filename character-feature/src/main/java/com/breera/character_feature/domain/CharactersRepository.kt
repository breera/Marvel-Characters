package com.breera.character_feature.domain

import com.breera.character_feature.domain.model.CharactersModel

/**
 * Created by Breera Hanif on 05/01/2025.
 */

interface CharactersRepository {
    suspend fun getCharacters(): CharactersModel
}