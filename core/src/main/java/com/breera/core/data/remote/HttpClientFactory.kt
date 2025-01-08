package com.breera.core.data.remote

import com.breera.core.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.Calendar
import java.util.TimeZone

/**
 * HttpClientFactory is responsible for creating and configuring an instance of HttpClient.
 * It sets up various plugins and configurations to handle JSON content, logging, and timeouts.
 */
object HttpClientFactory {

    /**
     * Creates an instance of HttpClient using the provided HttpClientEngine.
     *
     * @param engine The HttpClientEngine to be used by the HttpClient.
     * @return A configured HttpClient instance.
     */
    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            // Install ContentNegotiation plugin for handling JSON serialization/deserialization
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true // Ignore unknown keys in JSON responses
                    }
                )
            }
            // Install HttpTimeout plugin to set socket and request timeouts
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L // Set socket timeout to 20 seconds
                requestTimeoutMillis = 20_000L // Set request timeout to 20 seconds
            }
            // Install Logging plugin to log HTTP requests and responses
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message) // Print log messages to the console
                    }
                }
                level = LogLevel.ALL // Log all HTTP interactions
            }
            install(DefaultRequest) {
                url {
                    val timestamp =
                        (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                    parameters.append("ts", timestamp)
                    parameters.append("apikey", BuildConfig.PUBLIC_API_KEY)
                    parameters.append(
                        "hash",
                        "$timestamp${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()
                    )

                }
            }
            // Set default request configuration
            defaultRequest {
                url {
                    host = BuildConfig.BASE_URL
                    protocol = URLProtocol.HTTPS
                }
                contentType(ContentType.Application.Json) // Set default content type to JSON
            }
        }
    }
}