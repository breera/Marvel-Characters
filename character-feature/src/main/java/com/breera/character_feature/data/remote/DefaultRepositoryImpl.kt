package com.breera.character_feature.data.remote

import com.breera.character_feature.data.dto.CharacterDto
import com.breera.character_feature.data.dto.SingleSectionDTO
import com.breera.character_feature.domain.model.Item
import com.breera.core.BuildConfig
import com.breera.core.data.remote.md5
import com.breera.core.data.remote.safeCall
import com.breera.core.domain.DataError
import com.breera.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.util.Calendar
import java.util.TimeZone

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

    /**
     * Fetches detailed information for a specified section of items asynchronously.
     *
     * This method overrides the base implementation to fetch data for a subset of items
     * within the given range [start, end). Each item's information is retrieved using
     * an HTTP GET request, and the results are wrapped in a `Result` object to handle
     * potential data errors gracefully. The method leverages coroutines to perform
     * asynchronous operations, ensuring that the UI remains responsive.
     *
     * @param section A list of `Item` objects representing the section to be processed.
     * @param start The starting index (inclusive) for the sublist of items to be fetched.
     * @param end The ending index (exclusive) for the sublist of items to be fetched.
     * @return A list of `Result` objects, each containing either a `SingleSectionDTO` on success
     *         or a `DataError` on failure, corresponding to each item in the specified range.
     */
    override suspend fun getSingleSectionInfo(
        section: List<Item>,
        start: Int,
        end: Int
    ): List<Result<SingleSectionDTO, DataError>> {
        return coroutineScope {
            section.subList(start, end).map { item ->
                async {
                    val response: Result<SingleSectionDTO, DataError> = safeCall {
                        httpClient.get(item.resourceURI ?: "") {
                            val timestamp =
                                (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                            url {
                                parameters.append("ts", timestamp)
                                parameters.append("apikey", BuildConfig.PUBLIC_API_KEY)
                                parameters.append(
                                    "hash",
                                    "$timestamp${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()
                                )
                            }
                        }
                    }
                    response
                }
            }.awaitAll()
        }
    }
}
