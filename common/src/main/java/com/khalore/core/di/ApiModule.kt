package com.khalore.core.di


import com.khalore.core.api.TranslateApi
import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.core.repository.analytics.AnalyticsRepositoryImpl
import com.khalore.core.repository.cards.CardsRepository
import com.khalore.core.repository.cards.CardsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {

    companion object {
        const val TRANSLATE_GOOGLE_DOMAIN = "https://translate.googleapis.com"
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(TRANSLATE_GOOGLE_DOMAIN)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun bindsTranslateApi(retrofit: Retrofit): TranslateApi = retrofit.create(TranslateApi::class.java)

}