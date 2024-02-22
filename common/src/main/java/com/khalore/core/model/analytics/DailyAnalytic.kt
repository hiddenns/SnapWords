package com.khalore.core.model.analytics

import android.util.Log
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

data class DailyAnalytic(
    val dayUtc: Long,
    val swipesCount: Long = 0,
    val addedCardsCount: Long = 0,
    val positiveSwipesCount: Long = 0
) {

    companion object {
        fun getUniqueDayUtcToday(): Long {
            // Get current UTC time
            val currentUtcInstant = Instant.now()

            // Format the current UTC date to include only the date part (yyyy-MM-dd)
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentDateUtcString =
                dateFormatter.format(currentUtcInstant.atZone(ZoneOffset.UTC))

            // Parse the formatted date string into a LocalDate object
            val currentDateUtc = LocalDate.parse(currentDateUtcString)

            // Print the unique value for the day in UTC (at midnight)
            val uniqueValue = currentDateUtc.toEpochDay()
            Log.d("utc", "unicDay: $uniqueValue")
            return uniqueValue
        }

        fun getUniqueDayUtc(utc: Long): Long {
            val currentUtcInstant = Instant.ofEpochSecond(utc)
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentDateUtcString =
                dateFormatter.format(currentUtcInstant.atZone(ZoneOffset.UTC))
            val currentDateUtc = LocalDate.parse(currentDateUtcString)
            val uniqueValue = currentDateUtc.toEpochDay()
            Log.d("utc", "getUniqueDayUtcToday: $uniqueValue")
            return uniqueValue
        }
    }
}
