package com.khalore.core.entity.analytics

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyAnalyticLocal(
    @PrimaryKey
    val dayUtc: Long,
    val swipesCount: Long,
    val addedCardsCount: Long,
    val positiveSwipesCount: Long
)
