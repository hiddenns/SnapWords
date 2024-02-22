package com.khalore.core.entity.analytics

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyAnalyticLocal(
    @PrimaryKey
    var dayUtc: Long = 0,
    val swipesCount: Long,
    val addedCardsCount: Long,
    val positiveSwipesCount: Long
)
