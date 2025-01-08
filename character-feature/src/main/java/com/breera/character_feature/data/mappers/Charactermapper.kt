package com.breera.character_feature.data.mappers

import com.breera.character_feature.data.dto.CharacterDto.Data
import com.breera.character_feature.domain.model.Characters
import com.breera.character_feature.domain.model.Comics
import com.breera.character_feature.domain.model.Events
import com.breera.character_feature.domain.model.Item
import com.breera.character_feature.domain.model.Series
import com.breera.character_feature.domain.model.Stories
import com.breera.character_feature.domain.model.Thumbnail

/**
 * Created by Breera Hanif on 08/01/2025.
 * Mapper to convert Data Model into Domain Model
 */

fun List<Data.Result>.toCharacterModel(): List<Characters> {
    return this.map {
        Characters(
            id = it.id,
            thumbnail = Thumbnail(
                extension = it.thumbnail?.extension, path = it.thumbnail?.path
            ),
            name = it.name,
            description = it.description,
            comics = Comics(
                collectionURI = it.comics?.collectionURI,
                items = it.comics?.items?.map { items ->
                    Item(
                        name = items?.name, resourceURI = items?.resourceURI

                    )
                }
            ),
            series = Series(
                collectionURI = it.series?.collectionURI,
                items = it.series?.items?.map { items ->
                    Item(
                        name = items?.name, resourceURI = items?.resourceURI

                    )
                }
            ),
            stories = Stories(
                collectionURI = it.stories?.collectionURI,
                items = it.stories?.items?.map { items ->
                    Item(
                        name = items?.name, resourceURI = items?.resourceURI

                    )
                }
            ),
            events = Events(
                collectionURI = it.events?.collectionURI,
                items = it.events?.items?.map { items ->
                    Item(
                        name = items?.name, resourceURI = items?.resourceURI

                    )
                }
            ),
            resourceURI = it.resourceURI,
            urls = listOf()
        )
    }
}
