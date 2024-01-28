package com.khalore.core.repository

import com.khalore.core.datasource.cards.CardsLocalDataSource
import com.khalore.core.datasource.wordcombination.WordCombinationLocalDataSource
import com.khalore.core.mappers.toLocal
import com.khalore.core.model.card.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val cardsLocalDataSource: CardsLocalDataSource,
    private val wordCombinationLocalDataSource: WordCombinationLocalDataSource
) : CardsRepository {

    override fun getCardsFlow(): Flow<List<Card>> {
        return cardsLocalDataSource.getCardsFlow()
    }

    override fun getCardByIdFlow(cardId: Long): Flow<Card> {
        return cardsLocalDataSource.getCardByIdFlow(cardId)
    }

    override suspend fun insert(card: Card): Unit = withContext(Dispatchers.IO) {
        val savedWordCombination = wordCombinationLocalDataSource.insert(card.wordCombination)
        val updatedCard = card.toLocal().copy(wordCombinationId = savedWordCombination)
        cardsLocalDataSource.insert(updatedCard)
    }

    override fun insert(cards: List<Card>) {
        cardsLocalDataSource.insert(cards)
    }

    override fun update(card: Card) {
        cardsLocalDataSource.update(card)
    }

    override suspend fun deleteById(cardId: Long) {
        cardsLocalDataSource.deleteById(cardId)
    }

    override suspend fun delete(card: Card) {
        wordCombinationLocalDataSource.deleteById(card.wordCombination.wordCombinationId)
        cardsLocalDataSource.delete(card)
    }
}