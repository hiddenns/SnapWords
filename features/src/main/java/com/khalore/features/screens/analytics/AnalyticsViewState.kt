package com.khalore.features.screens.analytics

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.khalore.core.model.analytics.DailyAnalytic

data class AnalyticsViewState(
    val textToNumberAnalyticsList: List<TextToNumberAnalyticsItemUI>,
    val weekDailyAnalyticsList: List<DailyAnalytic>
)

data class TextToNumberAnalyticsItemUI(
    @StringRes val message: Int = 0,
    @DrawableRes val icon: Int = 0,
    val count: Long = 0,
    val viewType: AnalyticsViewType = AnalyticsViewType.DATA
)

enum class AnalyticsViewType {
    DATA, MORE;
}