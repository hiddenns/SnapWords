package com.khalore.core.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.khalore.core.entity.analytics.DailyAnalyticLocal

@Dao
interface AnalyticsDao {

    @Query("SELECT * FROM DailyAnalyticLocal WHERE dayUtc = :utc")
    fun getOneDayAnalyticsByDay(utc: Long): DailyAnalyticLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyAnalytic: DailyAnalyticLocal) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyAnalytic: List<DailyAnalyticLocal>)

    @Update
    suspend fun update(dailyAnalytic: DailyAnalyticLocal)

    @Query("DELETE FROM DailyAnalyticLocal WHERE dayUtc = :dateUtc")
    suspend fun deleteByDate(dateUtc: Long)

    @Delete
    suspend fun delete(dailyAnalytic: DailyAnalyticLocal)
}