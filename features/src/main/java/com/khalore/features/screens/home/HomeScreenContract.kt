package com.khalore.features.screens.home

import com.khalore.core.base.ViewEvent
import com.khalore.core.base.ViewSideEffect
import com.khalore.core.base.ViewState
import com.khalore.core.model.card.Card
import com.khalore.core.base.State as BaseState

class HomeScreenContract {
    sealed class Event : ViewEvent {
        data class SetupCards(
            val cardList: List<Card>
        ) : Event()
    }

    data class State(
        val state: BaseState<HomeViewState>
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}