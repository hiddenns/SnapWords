package com.khalore.core.mappers

import com.khalore.core.entity.analytics.DailyAnalyticLocal
import com.khalore.core.model.analytics.DailyAnalytic

fun DailyAnalytic.toLocal(): DailyAnalyticLocal {
    return DailyAnalyticLocal(
        dayUtc = this@toLocal.dayUtc,
        addedCardsCount = this@toLocal.addedCardsCount,
        swipesCount = this@toLocal.swipesCount,
        positiveSwipesCount = this@toLocal.positiveSwipesCount
    )
}

fun DailyAnalyticLocal.toDomain(): DailyAnalytic {
    return DailyAnalytic(
        dayUtc = this@toDomain.dayUtc,
        addedCardsCount = this@toDomain.addedCardsCount,
        swipesCount = this@toDomain.swipesCount,
        positiveSwipesCount = this@toDomain.positiveSwipesCount
    )
}