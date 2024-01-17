package com.khalore.core.di


import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
//    @Singleton
//    @Binds
//    fun bindsCardEntityRepository(
//        cardEntityRepository: DefaultCardEntityRepository
//    ): CardEntityRepository
}