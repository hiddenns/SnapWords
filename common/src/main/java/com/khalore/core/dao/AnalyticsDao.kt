package com.khalore.core.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khalore.core.entity.analytics.DailyAnalyticLocal

@Dao
interface AnalyticsDao {

    @Query("SELECT SUM(DailyAnalyticLocal.swipesCount) FROM DailyAnalyticLocal")
    suspend fun getTotalSwipes(): Long

    @Query("SELECT COUNT(CardLocal.cardId) FROM CardLocal")
    suspend fun getTotalCards(): Long

    @Query("SELECT * FROM DailyAnalyticLocal WHERE dayUtc = :utc")
    suspend fun getOneDayAnalyticsByDay(utc: Long): DailyAnalyticLocal?

    @Query("SELECT AVG(DailyAnalyticLocal.swipesCount) FROM DailyAnalyticLocal")
    suspend fun getAverageSwipesPerDays(): Long

    @Query("SELECT * FROM DailyAnalyticLocal ORDER BY DailyAnalyticLocal.dayUtc DESC")
    suspend fun getAllDailyAnalytics(): List<DailyAnalyticLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyAnalytic: DailyAnalyticLocal) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyAnalytic: List<DailyAnalyticLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(dailyAnalytic: DailyAnalyticLocal) : Long

    @Query("DELETE FROM DailyAnalyticLocal WHERE dayUtc = :dateUtc")
    suspend fun deleteByDate(dateUtc: Long)

    @Delete
    suspend fun delete(dailyAnalytic: DailyAnalyticLocal)
}