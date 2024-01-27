package com.khalore.core.di


import com.khalore.core.datasource.CardsLocalDataSource
import com.khalore.core.datasource.CardsLocalDataSourceImpl
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

}