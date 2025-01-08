package com.breera.marvelcharactersapp

import com.breera.character_feature.domain.model.Characters
import kotlinx.serialization.Serializable


/**
 * Defines the navigation routes for different screens within the Marvel Characters App.
 *
 * The `Route` sealed interface is used to represent the various screens available in the app,
 * providing a structured way to manage navigation between them. Each route corresponds to a
 * specific screen or navigation path, ensuring type-safe navigation within the app's architecture.
 */

sealed interface Route {

    @Serializable
    data object CharacterGraph : Route

    @Serializable
    data object CharacterHome : Route

    @Serializable
    data object CharacterDetail : Route

    @Serializable
    data class CharacterPager(val characters: Characters) : Route
}
