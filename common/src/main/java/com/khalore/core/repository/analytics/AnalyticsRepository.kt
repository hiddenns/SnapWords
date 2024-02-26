package com.khalore.core.repository.analytics

import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.model.card.Card

interface AnalyticsRepository {

    suspend fun getTotalSwipes(): Long

    suspend fun getTotalCards(): Long

    suspend fun getAverageSwipesPerDays(): Long

    suspend fun getSwipesDaysInRow(): Long

    suspend fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic?

    suspend fun increaseSwipesCount(card: Card, positiveAnswer: Boolean)

    suspend fun increaseAddedCardsCount(card: Card) : Long

    suspend fun insert(dailyAnalytic: DailyAnalytic) : Long

    suspend fun insert(dailyAnalytic: List<DailyAnalytic>)

    suspend fun update(dailyAnalytic: DailyAnalytic) : Long

    suspend fun deleteByDate(dateUtc: Long)

    suspend fun delete(dailyAnalytic: DailyAnalytic)
}