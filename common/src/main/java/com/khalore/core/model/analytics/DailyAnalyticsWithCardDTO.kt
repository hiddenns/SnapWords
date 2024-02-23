package com.khalore.core.model.analytics

import androidx.room.Embedded
import androidx.room.Relation
import com.khalore.core.entity.CardLocal
import com.khalore.core.entity.analytics.DailyAnalyticLocal

data class DailyAnalyticsWithCardDTO(
    @Embedded
    val dailyAnalyticLocal: DailyAnalyticLocal,
    @Relation(
        parentColumn = "dayUtc",
        entityColumn = "dayUtc"
    )
    val cards: List<CardLocal>
)