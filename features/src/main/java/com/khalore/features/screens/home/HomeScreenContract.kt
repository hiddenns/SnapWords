package com.khalore.features.screens.home

import com.khalore.core.base.ViewEvent
import com.khalore.core.base.ViewSideEffect
import com.khalore.core.base.ViewState
import com.khalore.core.model.card.Card
import com.khalore.features.components.cards.SwippedCardState
import com.khalore.core.base.State as BaseState

class HomeScreenContract {
    sealed class Event : ViewEvent {
        data class SetupCards(
            val cardList: List<Card>
        ) : Event()

        data class SwipeCard(val swippedCardState: SwippedCardState) : Event()
        data object RotateCard : Event()

        sealed class Navigation : Event() {
            data object ToCollectionScreen : Event()
            data object ToInfoScreen : Event()
            data object ToAnalyticScreen : Event()
        }
    }

    data class State(
        val state: BaseState<HomeViewState>
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToCollectionScreen : Navigation()
            data object ToInfoScreen : Navigation()
            data object ToAnalyticScreen : Navigation()
        }
    }
}