package com.khalore.core.usecase.analytics

import com.khalore.core.model.card.Card
import com.khalore.core.repository.analytics.AnalyticsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IncreaseSwipesCountUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) {

    suspend operator fun invoke(card: Card, positiveAnswer: Boolean) = withContext(Dispatchers.IO) {
         analyticsRepository.increaseSwipesCount(card, positiveAnswer)
    }

}