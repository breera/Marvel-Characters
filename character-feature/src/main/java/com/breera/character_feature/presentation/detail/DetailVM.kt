package com.breera.character_feature.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.breera.character_feature.domain.GetSectionInfoUseCase
import com.breera.character_feature.domain.model.SingleSectionInfoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

/**
 * Created by Breera Hanif on 06/01/2025.
 * ViewModel for managing the state and actions of the detail screen.
 *
 * `DetailVM` is responsible for holding and updating the state of the detail view,
 * reacting to user actions, and ensuring the UI is in sync with the underlying data.
 * It uses a state flow to manage the `DetailState`, providing a reactive way to update
 * and observe the state changes.
 */

class DetailVM(private val getSectionInfoUseCase: GetSectionInfoUseCase) : ViewModel() {
    private val _characterDetailState = MutableStateFlow(DetailState())
    val characterDetailState = _characterDetailState.asStateFlow()

    /**
     * A flow that provides paginated information about the comic section of a character.
     *
     * This flow filters the `characterDetailState` to ensure that the comic section is non-empty,
     * then uses the `getSectionInfoUseCase` to transform the list of comic items into a paginated
     * flow of `SingleSectionInfoModel`. It leverages `flatMapLatest` to ensure that only the latest
     * section information is emitted, canceling any previous emissions if new data arrives.
     */

    @OptIn(ExperimentalCoroutinesApi::class)
    val comicSectionInfo: Flow<PagingData<SingleSectionInfoModel>> = characterDetailState
        .filter { it.character?.comics?.items?.isNotEmpty() == true } // Ensure the section is non-empty
        .flatMapLatest { section ->
            getSectionInfoUseCase(
                section.character?.comics?.items ?: emptyList()
            )
        }

    /**
     * A flow that provides paginated information about the stories section of a character.
     *
     * This flow filters the `characterDetailState` to ensure that the stories section is non-empty,
     * then uses the `getSectionInfoUseCase` to transform the list of story items into a paginated
     * flow of `SingleSectionInfoModel`. It uses `flatMapLatest` to handle only the most recent data
     * updates, canceling previous emissions if new data arrives.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val storiesSectionInfo: Flow<PagingData<SingleSectionInfoModel>> = characterDetailState
        .filter { it.character?.stories?.items?.isNotEmpty() == true } // Ensure the section is non-empty
        .flatMapLatest { section ->
            getSectionInfoUseCase(
                section.character?.stories?.items ?: emptyList()
            )
        }

    /**
     * A flow that provides paginated information about the events section of a character.
     *
     * This flow filters the `characterDetailState` to ensure that the events section is non-empty,
     * then uses the `getSectionInfoUseCase` to transform the list of event items into a paginated
     * flow of `SingleSectionInfoModel`. It employs `flatMapLatest` to ensure only the latest section
     * information is emitted, canceling any prior emissions when new data becomes available.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val eventsSectionInfo: Flow<PagingData<SingleSectionInfoModel>> = characterDetailState
        .filter { it.character?.events?.items?.isNotEmpty() == true } // Ensure the section is non-empty
        .flatMapLatest { section ->
            getSectionInfoUseCase(
                section.character?.events?.items ?: emptyList()
            )
        }

    /**
     * A flow that provides paginated information about the series section of a character.
     *
     * This flow filters the `characterDetailState` to ensure that the series section is non-empty,
     * then uses the `getSectionInfoUseCase` to transform the list of series items into a paginated
     * flow of `SingleSectionInfoModel`. It utilizes `flatMapLatest` to ensure that only the most
     * recent section information is emitted, canceling any previous emissions if new data arrives.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    val seriesSectionInfo: Flow<PagingData<SingleSectionInfoModel>> = characterDetailState
        .filter { it.character?.series?.items?.isNotEmpty() == true } // Ensure the section is non-empty
        .flatMapLatest { section ->
            getSectionInfoUseCase(
                section.character?.series?.items ?: emptyList()
            )
        }

    /**
     * Handles actions dispatched from the UI, updating the state accordingly.
     *
     * @param action The action to be handled, defined by the `DetailAction` sealed interface.
     */
    fun onAction(action: DetailAction) {
        when (action) {

            is DetailAction.OnSelectedCharacterChange -> {
                _characterDetailState.update {
                    it.copy(character = action.characters)
                }
            }

            else -> {
                // nothing to do for now
            }
        }
    }
}