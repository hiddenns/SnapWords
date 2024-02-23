package com.khalore.features.screens.analytics

import com.khalore.core.base.ViewEvent
import com.khalore.core.base.ViewSideEffect
import com.khalore.core.base.ViewState
import com.khalore.core.base.State as BaseState

class AnalyticsScreenContract {
    sealed class Event : ViewEvent {
        data class SetupAnalyticState(val state: AnalyticsViewState) : Event()
    }

    data class State(
        val state: BaseState<AnalyticsViewState>
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object Navigation: Effect()
    }
}