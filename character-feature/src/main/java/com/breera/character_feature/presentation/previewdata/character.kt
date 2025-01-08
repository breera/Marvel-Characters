package com.breera.character_feature.presentation.previewdata

import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Comics
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.Stories
import com.breera.character_feature.domain.model.Thumbnail

val character = Characters(
    id = 1,
    name = "Long Long Name Long",
    description = "sdsd",
    thumbnail = Thumbnail(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
        extension = "jpg"
    ),
    comics = Comics(
        items = listOf(
            Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ), Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
        )
    ),
    stories = Stories(
        items = listOf(
            Item(
                name = "Comic",
                resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
        )
    )
)