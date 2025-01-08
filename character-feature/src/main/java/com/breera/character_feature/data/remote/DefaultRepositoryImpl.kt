package com.breera.character_feature.data.remote

import com.breera.character_feature.data.dto.CharacterDto
import com.breera.core.data.remote.safeCall
import com.breera.core.domain.DataError
import com.breera.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Breera Hanif on 07/01/2025.
 * DefaultRepositoryImpl is the concrete implementation of the DefaultRepository interface.
 * This class is the data retrieval specialist, making HTTP requests like a pro.
 * */
class DefaultRepositoryImpl(private val httpClient: HttpClient) : DefaultRepository {
    override suspend fun getCharacters(offset: Int, limit: Int): Result<CharacterDto, DataError> {
        // Use safeCall to wrap the HTTP request
        return safeCall {
            // Make a GET request to the API endpoint
            httpClient.get("/v1/public/characters?&limit=$limit&offset=$offset")
        }
    }
}