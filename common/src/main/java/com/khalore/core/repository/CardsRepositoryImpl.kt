package com.khalore.core.repository

import com.khalore.core.datasource.CardsLocalDataSource
import com.khalore.core.model.card.Card
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val cardsLocalDataSource: CardsLocalDataSource
) : CardsRepository {

    override fun getCardsFlow(): Flow<List<Card>> {
        return cardsLocalDataSource.getCardsFlow()
    }

    override fun getCardByIdFlow(cardId: Long): Flow<Card> {
        return cardsLocalDataSource.getCardByIdFlow(cardId)
    }

    override fun insert(card: Card) {
        cardsLocalDataSource.insert(card)
    }

    override fun insert(cards: List<Card>) {
        cardsLocalDataSource.insert(cards)
    }

    override fun update(card: Card) {
        cardsLocalDataSource.update(card)
    }

    override fun deleteById(cardId: Long) {
        cardsLocalDataSource.deleteById(cardId)
    }

    override fun delete(card: Card) {
        cardsLocalDataSource.delete(card)
    }
}