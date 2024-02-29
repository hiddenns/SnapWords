package com.khalore.features.screens.analytics

import androidx.annotation.DrawableRes
import com.khalore.core.model.analytics.DailyAnalytic

data class AnalyticsViewState(
    val textToNumberAnalyticsList: List<TextToNumberAnalyticsItem>,
    val weekDailyAnalyticsList: List<DailyAnalytic>
)

data class TextToNumberAnalyticsItem(
    val message: String,
    val count: Long,
    @DrawableRes val icon: Int
)