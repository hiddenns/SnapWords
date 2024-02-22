package com.khalore.core.di


import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.core.repository.analytics.AnalyticsRepositoryImpl
import com.khalore.core.repository.cards.CardsRepository
import com.khalore.core.repository.cards.CardsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsCardRepository(
        cardRepository: CardsRepositoryImpl
    ): CardsRepository

    @Singleton
    @Binds
    fun bindsAnalyticsRepository(
        analyticsRepositoryImpl: AnalyticsRepositoryImpl
    ): AnalyticsRepository
}