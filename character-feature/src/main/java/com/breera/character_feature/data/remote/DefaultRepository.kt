package com.breera.character_feature.data.remote

import com.breera.character_feature.data.dto.CharacterDto
import com.breera.core.domain.DataError
import com.breera.core.domain.Result

/**
 * Created by Breera Hanif on 07/01/2025.
 *
 * The CharactersRepository interface for data layer.
 * */

interface DefaultRepository {
    suspend fun getCharacters(offset: Int, limit: Int): Result<CharacterDto, DataError>
}