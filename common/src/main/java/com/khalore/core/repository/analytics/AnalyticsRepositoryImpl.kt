package com.khalore.core.repository.analytics

import android.icu.util.Calendar
import com.khalore.core.datasource.analytics.AnalyticsLocalDataSource
import com.khalore.core.ext.timeTodayMillis
import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.model.card.Card
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val local: AnalyticsLocalDataSource
) : AnalyticsRepository {

    override fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic? {
        return local.getOneDayAnalyticsByDay(utc)
    }

    override suspend fun insert(dailyAnalytic: DailyAnalytic): Long {
        return local.insert(dailyAnalytic)
    }

    override suspend fun insert(dailyAnalytic: List<DailyAnalytic>) {
        return local.insert(dailyAnalytic)
    }

    override suspend fun increaseSwipesCount(card: Card, positiveAnswer: Boolean) {
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

    override suspend fun increaseAddedCardsCount(card: Card) {
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

    override suspend fun update(dailyAnalytic: DailyAnalytic) {
        return local.update(dailyAnalytic)
    }

    override suspend fun deleteByDate(dateUtc: Long) {
        return local.deleteByDate(dateUtc)
    }

    override suspend fun delete(dailyAnalytic: DailyAnalytic) {
        return local.delete(dailyAnalytic)
    }


}