package com.khalore.core.repository.analytics

import android.icu.util.Calendar
import com.khalore.core.datasource.analytics.AnalyticsLocalDataSource
import com.khalore.core.ext.timeTodayMillis
import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.model.card.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val local: AnalyticsLocalDataSource
) : AnalyticsRepository {

    override suspend fun getTotalSwipes(): Long = withContext(Dispatchers.IO) {
        local.getTotalSwipes()
    }

    override suspend fun getTotalCards(): Long = withContext(Dispatchers.IO) {
        local.getTotalCards()
    }

    override suspend fun getAverageSwipesPerDays(): Long = withContext(Dispatchers.IO) {
        local.getAverageSwipesPerDays()
    }

    override suspend fun getSwipesDaysInRow(): Long = withContext(Dispatchers.IO) {
        local.getSwipesDaysInRow()
    }

    override suspend fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic? =
        withContext(Dispatchers.IO) {
            local.getOneDayAnalyticsByDay(utc)
        }

    override suspend fun insert(dailyAnalytic: DailyAnalytic): Long = withContext(Dispatchers.IO) {
        local.insert(dailyAnalytic)
    }

    override suspend fun insert(dailyAnalytic: List<DailyAnalytic>) = withContext(Dispatchers.IO) {
        local.insert(dailyAnalytic)
    }

    override suspend fun increaseSwipesCount(card: Card, positiveAnswer: Boolean): Unit =
        withContext(Dispatchers.IO) {
            val calendar = Calendar.getInstance()
            val timeTodayMillis = calendar.timeTodayMillis

            val todayAnalytics = getOneDayAnalyticsByDay(timeTodayMillis)
            val todayAnalyticUpdated = todayAnalytics?.let { today ->
                val result = if (positiveAnswer) {
                    today.copy(positiveSwipesCount = todayAnalytics.positiveSwipesCount + 1)
                } else {
                    today
                }
                result.copy(swipesCount = todayAnalytics.swipesCount + 1)
            }

            if (todayAnalyticUpdated == null) {
                insert(DailyAnalytic(timeTodayMillis).copy(swipesCount = 1))
            } else {
                update(todayAnalyticUpdated)
            }
        }

    override suspend fun increaseAddedCardsCount(card: Card): Long = withContext(Dispatchers.IO) {
        val calendar = Calendar.getInstance()
        val timeTodayMillis = calendar.timeTodayMillis

        val todayAnalytics = getOneDayAnalyticsByDay(timeTodayMillis)
        val todayAnalyticUpdated =
            todayAnalytics?.copy(addedCardsCount = todayAnalytics.addedCardsCount + 1)

        if (todayAnalyticUpdated == null) {
            insert(DailyAnalytic(timeTodayMillis))
        } else {
            update(todayAnalyticUpdated)
        }
    }

    override suspend fun update(dailyAnalytic: DailyAnalytic): Long = withContext(Dispatchers.IO) {
        local.update(dailyAnalytic)
    }

    override suspend fun deleteByDate(dateUtc: Long) = withContext(Dispatchers.IO) {
        local.deleteByDate(dateUtc)
    }

    override suspend fun delete(dailyAnalytic: DailyAnalytic) = withContext(Dispatchers.IO) {
        local.delete(dailyAnalytic)
    }

    override suspend fun getWeekDailyAnalytics(): List<DailyAnalytic?> =
        withContext(Dispatchers.IO) {
            val analytics = local.getWeekDailyAnalytics()
            val result = mutableListOf<Long?>()
            val oneDayInMillis = TimeUnit.DAYS.toMillis(1)

            if (analytics.isEmpty()) return@withContext emptyList()
            val start = analytics.firstOrNull()?.dayUtc
            result.add(start)

            for (i in 1..6) {
                result.add(result.lastOrNull()?.minus(oneDayInMillis))
            }

            result.map { day ->
                analytics.find { it.dayUtc == day }
            }
        }

}