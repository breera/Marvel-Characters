package com.breera.character_feature.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharactersModel(
    var results: List<Characters>? = listOf()
)

@Serializable
data class Characters(
    var id: Int? = 0,
    var thumbnail: Thumbnail? = Thumbnail(),
    var name: String? = "",
    var description: String? = "",
    var comics: Comics? = Comics(),
    var series: Series? = Series(),
    var stories: Stories? = Stories(),
    var events: Events? = Events(),
    var resourceURI: String? = "",
    var urls: List<Url?>? = listOf()
)

@Serializable
data class Comics(
    var collectionURI: String? = "",
    var items: List<Item>? = listOf(),
)

@Serializable
data class Item(
    var name: String? = "",
    var resourceURI: String? = ""
)

@Serializable
data class Events(
    var collectionURI: String? = "",
    var items: List<Item>? = listOf()
)

@Serializable
data class Series(
    var collectionURI: String? = "",
    var items: List<Item>? = listOf()
)


@Serializable
data class Stories(
    var collectionURI: String? = "",
    var items: List<Item>? = listOf()
)

@Serializable
data class Thumbnail(
    var extension: String? = "",
    var path: String? = ""
)

@Serializable
data class Url(
    var type: String? = "",
    var url: String? = ""
)
