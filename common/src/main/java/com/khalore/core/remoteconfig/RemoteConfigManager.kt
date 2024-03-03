package com.khalore.core.remoteconfig

interface RemoteConfigManager {
    companion object {

        fun create(): RemoteConfigManager {
            return RemoteConfigManagerImpl()
        }

        internal const val FETCH_INTERVAL = 100L
        internal const val CONNECT_IN_TELEGRAM_USER_ID = "connect_telegram_user_id"
        internal const val GOOGLE_PLAY_LINK = "google_play_link"
    }

    fun fetchConfig()
    fun getTelegramContactUserId(): String
    fun getGooglePlayLink(): String
}