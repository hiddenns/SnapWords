package com.khalore.core.di


import com.khalore.core.datasource.remote.translate.TranslateRemoteDataSource
import com.khalore.core.datasource.remote.translate.TranslateRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourceModule {

    @Singleton
    @Binds
    fun bindsTranslateRemoteDataSource(
        translateRemoteDataSource: TranslateRemoteDataSourceImpl
    ): TranslateRemoteDataSource

}