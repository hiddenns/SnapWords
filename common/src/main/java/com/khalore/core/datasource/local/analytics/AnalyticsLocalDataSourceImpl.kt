package com.khalore.core.datasource.local.analytics

import android.util.Log
import com.khalore.core.dao.AnalyticsDao
import com.khalore.core.mappers.toDomain
import com.khalore.core.mappers.toLocal
import com.khalore.core.model.analytics.DailyAnalytic
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AnalyticsLocalDataSourceImpl @Inject constructor(
    private val dao: AnalyticsDao
) : AnalyticsLocalDataSource {

    override suspend fun getTotalSwipes(): Long {
        return dao.getTotalSwipes()
    }

    override suspend fun getTotalCards(): Long {
        return dao.getTotalCards()
    }

    override suspend fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic? {
        return dao.getOneDayAnalyticsByDay(utc)?.toDomain()
    }

    override suspend fun getAverageSwipesPerDays(): Long {
        return dao.getAverageSwipesPerDays()
    }

    override suspend fun getSwipesDaysInRow(): Long {
        return dao.getAllDailyAnalytics().map {
            it.dayUtc
        }.let {
            countConsecutiveDays(it)
        }.toLong()
    }

    private fun countConsecutiveDays(days: List<Long>): Int {
        if (days.isEmpty()) return 0
        val oneDayInMillis = TimeUnit.DAYS.toMillis(1)
        var currentConsecutive = 1

        for (i in 1 until days.size) {
            if (days[i - 1] == days[i] + oneDayInMillis) {
                currentConsecutive++
            } else {
                break
            }
        }
        return currentConsecutive
    }

    override suspend fun insert(dailyAnalytic: DailyAnalytic): Long {
        return dao.insert(dailyAnalytic.toLocal())
    }

    override suspend fun insert(dailyAnalytic: List<DailyAnalytic>) {
        return dao.insert(dailyAnalytic.map {
            it.toLocal()
        })
    }

    override suspend fun update(dailyAnalytic: DailyAnalytic): Long {
        Log.d("anal", "update: ${dailyAnalytic.dayUtc}")
        return dao.update(dailyAnalytic.toLocal().also {
            Log.d("anal", "update local: ${dailyAnalytic.dayUtc}")
        })
    }

    override suspend fun deleteByDate(dateUtc: Long) {
        dao.deleteByDate(dateUtc)
    }

    override suspend fun delete(dailyAnalytic: DailyAnalytic) {
        dao.delete(dailyAnalytic.toLocal())
    }

    override suspend fun getWeekDailyAnalytics(): List<DailyAnalytic> {
        return dao.getWeekDailyAnalytics().map {
            it.toDomain()
        }
    }

}