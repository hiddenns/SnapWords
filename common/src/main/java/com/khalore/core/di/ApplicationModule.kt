package com.khalore.core.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.khalore.core.analyticmanager.AnalyticManager
import com.khalore.core.analyticmanager.AnalyticManagerImpl
import com.khalore.core.remoteconfig.RemoteConfigManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {

    @Binds
    fun bindsAnalyticManager(impl: AnalyticManagerImpl): AnalyticManager


    companion object {
        @Singleton
        @Provides
        fun providesRemoteConfig() = RemoteConfigManager.create()

        @Singleton
        @Provides
        fun provideFirebaseAnalytic(@ApplicationContext context: Context) =
            FirebaseAnalytics.getInstance(context)


    }

}