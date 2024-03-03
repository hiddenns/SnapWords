package com.khalore.features.screens.settings

import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.remoteconfig.RemoteConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val remoteConfigManager: RemoteConfigManager
) : BaseViewModel<
        SettingsScreenContract.Event,
        SettingsScreenContract.State,
        SettingsScreenContract.Effect>() {

    init {
        fetchRemoteLinks()
    }

    override fun setInitialState() = SettingsScreenContract.State(State.Loading)

    override fun handleEvents(event: SettingsScreenContract.Event) {
        when (event) {
            else -> {}
        }
    }

    private fun fetchRemoteLinks() {
        viewModelScope.launch {
            val telegramLink = remoteConfigManager.getTelegramContactUserId()
            val googlePlayLink = remoteConfigManager.getGooglePlayLink()

            setState {
                SettingsScreenContract.State(
                    State.Data(
                        SettingsViewState(
                            googlePlayLink = googlePlayLink,
                            telegramContactUserId = telegramLink
                        )
                    )
                )
            }
        }
    }


}