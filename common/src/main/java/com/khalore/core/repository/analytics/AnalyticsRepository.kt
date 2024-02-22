package com.khalore.core.repository.analytics

import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.model.card.Card

interface AnalyticsRepository {

    fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic?

    suspend fun increaseSwipesCount(card: Card, positiveAnswer: Boolean)

    suspend fun increaseAddedCardsCount(card: Card)

    suspend fun insert(dailyAnalytic: DailyAnalytic) : Long

    suspend fun insert(dailyAnalytic: List<DailyAnalytic>)

    suspend fun update(dailyAnalytic: DailyAnalytic)

    suspend fun deleteByDate(dateUtc: Long)

    suspend fun delete(dailyAnalytic: DailyAnalytic)
}