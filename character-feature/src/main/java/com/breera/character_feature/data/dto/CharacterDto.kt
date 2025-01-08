package com.breera.character_feature.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object representing a character from the Marvel API.
 *
 * This class is a comprehensive representation of the response received from the API,
 * including metadata and nested data structures for detailed character information.
 */
@Serializable
data class CharacterDto(
    @SerialName("attributionHTML")
    var attributionHTML: String? = "",
    @SerialName("attributionText")
    var attributionText: String? = "",
    @SerialName("code")
    var code: Int? = 0,
    @SerialName("copyright")
    var copyright: String? = "",
    @SerialName("data")
    var data: Data? = Data(),
    @SerialName("etag")
    var etag: String? = "",
    @SerialName("status")
    var status: String? = ""
) {
    @Serializable
    data class Data(
        @SerialName("count")
        var count: Int? = 0,
        @SerialName("limit")
        var limit: Int? = 0,
        @SerialName("offset")
        var offset: Int? = 0,
        @SerialName("results")
        var results: List<Result>? = listOf(),
        @SerialName("total")
        var total: Int? = 0
    ) {
        @Serializable
        data class Result(
            @SerialName("comics")
            var comics: Comics? = Comics(),
            @SerialName("description")
            var description: String? = "",
            @SerialName("events")
            var events: Events? = Events(),
            @SerialName("id")
            var id: Int? = 0,
            @SerialName("modified")
            var modified: String? = "",
            @SerialName("name")
            var name: String? = "",
            @SerialName("resourceURI")
            var resourceURI: String? = "",
            @SerialName("series")
            var series: Series? = Series(),
            @SerialName("stories")
            var stories: Stories? = Stories(),
            @SerialName("thumbnail")
            var thumbnail: Thumbnail? = Thumbnail(),
            @SerialName("urls")
            var urls: List<Url?>? = listOf()
        ) {
            @Serializable
            data class Comics(
                @SerialName("available")
                var available: Int? = 0,
                @SerialName("collectionURI")
                var collectionURI: String? = "",
                @SerialName("items")
                var items: List<Item?>? = listOf(),
                @SerialName("returned")
                var returned: Int? = 0
            ) {
                @Serializable
                data class Item(
                    @SerialName("name")
                    var name: String? = "",
                    @SerialName("resourceURI")
                    var resourceURI: String? = ""
                )
            }

            @Serializable
            data class Events(
                @SerialName("available")
                var available: Int? = 0,
                @SerialName("collectionURI")
                var collectionURI: String? = "",
                @SerialName("items")
                var items: List<Item?>? = listOf(),
                @SerialName("returned")
                var returned: Int? = 0
            ) {
                @Serializable
                data class Item(
                    @SerialName("name")
                    var name: String? = "",
                    @SerialName("resourceURI")
                    var resourceURI: String? = ""
                )
            }

            @Serializable
            data class Series(
                @SerialName("available")
                var available: Int? = 0,
                @SerialName("collectionURI")
                var collectionURI: String? = "",
                @SerialName("items")
                var items: List<Item?>? = listOf(),
                @SerialName("returned")
                var returned: Int? = 0
            ) {
                @Serializable
                data class Item(
                    @SerialName("name")
                    var name: String? = "",
                    @SerialName("resourceURI")
                    var resourceURI: String? = ""
                )
            }

            @Serializable
            data class Stories(
                @SerialName("available")
                var available: Int? = 0,
                @SerialName("collectionURI")
                var collectionURI: String? = "",
                @SerialName("items")
                var items: List<Item?>? = listOf(),
                @SerialName("returned")
                var returned: Int? = 0
            ) {
                @Serializable
                data class Item(
                    @SerialName("name")
                    var name: String? = "",
                    @SerialName("resourceURI")
                    var resourceURI: String? = "",
                    @SerialName("type")
                    var type: String? = ""
                )
            }

            @Serializable
            data class Thumbnail(
                @SerialName("extension")
                var extension: String? = "",
                @SerialName("path")
                var path: String? = ""
            )

            @Serializable
            data class Url(
                @SerialName("type")
                var type: String? = "",
                @SerialName("url")
                var url: String? = ""
            )
        }
    }
}