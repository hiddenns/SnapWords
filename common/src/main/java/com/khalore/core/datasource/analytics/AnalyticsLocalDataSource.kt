package com.khalore.core.datasource.analytics

import com.khalore.core.model.analytics.DailyAnalytic

interface AnalyticsLocalDataSource {

    fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic?

    suspend fun insert(dailyAnalytic: DailyAnalytic) : Long

    suspend fun insert(dailyAnalytic: List<DailyAnalytic>)

    suspend fun update(dailyAnalytic: DailyAnalytic)


    suspend fun deleteByDate(dateUtc: Long)

    suspend fun delete(dailyAnalytic: DailyAnalytic)
}