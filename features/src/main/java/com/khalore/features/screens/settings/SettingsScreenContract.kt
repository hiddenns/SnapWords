package com.khalore.features.screens.settings

import com.khalore.core.base.ViewEvent
import com.khalore.core.base.ViewSideEffect
import com.khalore.core.base.ViewState
import com.khalore.core.base.State as BaseState

class SettingsScreenContract {
    sealed class Event : ViewEvent {
        data object OnTelegramContactClick : Event()
        data object OnShareAppClick : Event()
    }

    data class State(
        val state: BaseState<SettingsViewState>
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {

        }
    }
}