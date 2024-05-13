package com.khalore.features.screens.collection

import com.khalore.core.base.ViewEvent
import com.khalore.core.base.ViewSideEffect
import com.khalore.core.base.ViewState
import com.khalore.core.model.card.Card
import com.khalore.core.base.State as BaseState

class CollectionScreenContract {
    sealed class Event : ViewEvent {
        data class AddCard(
            val card: Card
        ) : Event()

        data class DeleteCard(
            val card: Card
        ) : Event()

        data class UpdateCard(
            val card: Card
        ) : Event()

        data class SetupCards(
            val cards: List<Card>
        ) : Event()

        data class OnTranslateSentence(val sentence: String) : Event()

        data object AddDefaultCards : Event()
        data object OnClickFloatAdd : Event()
    }

    data class State(
        val state: BaseState<CollectionViewState>
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}