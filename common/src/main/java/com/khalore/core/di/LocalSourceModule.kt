package com.khalore.core.di


import com.khalore.core.datasource.local.analytics.AnalyticsLocalDataSource
import com.khalore.core.datasource.local.analytics.AnalyticsLocalDataSourceImpl
import com.khalore.core.datasource.local.cards.CardsLocalDataSource
import com.khalore.core.datasource.local.cards.CardsLocalDataSourceImpl
import com.khalore.core.datasource.local.wordcombination.WordCombinationLocalDataSource
import com.khalore.core.datasource.local.wordcombination.WordCombinationLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalSourceModule {

    @Singleton
    @Binds
    fun bindsCardDataSource(
        cardsLocalDataSourceImpl: CardsLocalDataSourceImpl
    ): CardsLocalDataSource

    @Singleton
    @Binds
    fun bindsWordsCombinationSource(
        wordCombinationLocalDataSource: WordCombinationLocalDataSourceImpl
    ): WordCombinationLocalDataSource

    @Singleton
    @Binds
    fun bindsAnalyticsSource(
        analyticsLocalDataSourceImpl: AnalyticsLocalDataSourceImpl
    ): AnalyticsLocalDataSource

}