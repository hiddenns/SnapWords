package com.khalore.core.di

import com.khalore.core.remoteconfig.RemoteConfigManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {


    companion object {
        @Singleton
        @Provides
        fun providesRemoteConfig() = RemoteConfigManager.create()

    }

}