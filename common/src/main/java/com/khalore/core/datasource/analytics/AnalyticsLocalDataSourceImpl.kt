package com.khalore.core.datasource.analytics

import android.util.Log
import com.khalore.core.dao.AnalyticsDao
import com.khalore.core.mappers.toDomain
import com.khalore.core.mappers.toLocal
import com.khalore.core.model.analytics.DailyAnalytic
import javax.inject.Inject

class AnalyticsLocalDataSourceImpl @Inject constructor(
    private val dao: AnalyticsDao
) : AnalyticsLocalDataSource {

    override fun getOneDayAnalyticsByDay(utc: Long): DailyAnalytic? {
        return dao.getOneDayAnalyticsByDay(utc)?.toDomain()
    }

    override suspend fun insert(dailyAnalytic: DailyAnalytic): Long {
        return dao.insert(dailyAnalytic.toLocal())
    }

    override suspend fun insert(dailyAnalytic: List<DailyAnalytic>) {
        return dao.insert(dailyAnalytic.map {
            it.toLocal()
        })
    }

    override suspend fun update(dailyAnalytic: DailyAnalytic) {
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

}