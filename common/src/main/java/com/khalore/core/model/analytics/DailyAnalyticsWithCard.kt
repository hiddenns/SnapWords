package com.khalore.core.model.analytics

import androidx.room.Embedded
import androidx.room.Relation
import com.khalore.core.entity.analytics.DailyAnalyticLocal
import com.khalore.core.model.card.Card

data class DailyAnalyticsWithCard(
    @Embedded
    val dailyAnalyticLocal: DailyAnalyticLocal,
    @Relation(
        parentColumn = "cardId",
        entityColumn = "dayUtc"
    )
    val albums: List<Card>
)