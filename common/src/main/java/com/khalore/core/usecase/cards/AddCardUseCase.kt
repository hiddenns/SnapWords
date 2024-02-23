package com.khalore.core.usecase.cards

import com.khalore.core.model.card.Card
import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.core.repository.cards.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCardUseCase @Inject constructor(
    private val cardsRepository: CardsRepository,
    private val analyticsRepository: AnalyticsRepository
) {

    suspend operator fun invoke(
        card: Card
    ) = withContext(Dispatchers.IO) {
        val dateUtcId = analyticsRepository.increaseAddedCardsCount(card)
        cardsRepository.insert(card.copy(dateUtc = dateUtcId))
    }

}