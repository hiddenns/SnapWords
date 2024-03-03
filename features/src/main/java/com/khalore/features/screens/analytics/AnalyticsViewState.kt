package com.khalore.features.screens.analytics

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.khalore.core.model.analytics.DailyAnalytic

data class AnalyticsViewState(
    val textToNumberAnalyticsList: List<TextToNumberAnalyticsItemUI>,
    val weekDailyAnalyticsList: List<DailyAnalytic>
)

data class TextToNumberAnalyticsItemUI(
    @StringRes val message: Int,
    @DrawableRes val icon: Int,
    val count: Long
)