package com.khalore.core.di


import com.khalore.core.datasource.analytics.AnalyticsLocalDataSource
import com.khalore.core.datasource.analytics.AnalyticsLocalDataSourceImpl
import com.khalore.core.datasource.cards.CardsLocalDataSource
import com.khalore.core.datasource.cards.CardsLocalDataSourceImpl
import com.khalore.core.datasource.wordcombination.WordCombinationLocalDataSource
import com.khalore.core.datasource.wordcombination.WordCombinationLocalDataSourceImpl
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