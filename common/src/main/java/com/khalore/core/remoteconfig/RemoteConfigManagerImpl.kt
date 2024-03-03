package com.khalore.core.remoteconfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.khalore.snapwords.R
import kotlinx.coroutines.flow.MutableStateFlow

class RemoteConfigManagerImpl: RemoteConfigManager {

    private val remoteConfig = Firebase.remoteConfig

    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = RemoteConfigManager.FETCH_INTERVAL
    }

    private val _remoteConfigFlow = MutableStateFlow<FirebaseRemoteConfig?>(null)

    override fun fetchConfig() {
        remoteConfig.apply {
            setDefaultsAsync(R.xml.remote_config_defaults)
            setConfigSettingsAsync(configSettings)
        }.fetchAndActivate().addOnCompleteListener {
            _remoteConfigFlow.value = remoteConfig
        }
    }

    override fun getTelegramContactUserId() = remoteConfig.getString(RemoteConfigManager.CONNECT_IN_TELEGRAM_USER_ID)

    override fun getGooglePlayLink() = remoteConfig.getString(RemoteConfigManager.GOOGLE_PLAY_LINK)
}