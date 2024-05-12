package com.khalore.core.datasource.local.analytics

import com.khalore.core.model.analytics.DailyAnalytic

interface AnalyticsLocalDataSource {

    suspend fun getTotalSwipes(): Long
    suspend fun getTotalCards(): Long
    suspend fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic?
    suspend fun getAverageSwipesPerDays(): Long
    suspend fun getSwipesDaysInRow(): Long
    suspend fun insert(dailyAnalytic: DailyAnalytic): Long
    suspend fun insert(dailyAnalytic: List<DailyAnalytic>)
    suspend fun update(dailyAnalytic: DailyAnalytic): Long
    suspend fun deleteByDate(dateUtc: Long)
    suspend fun delete(dailyAnalytic: DailyAnalytic)
    suspend fun getWeekDailyAnalytics(): List<DailyAnalytic>
}