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

    override suspend fun insert(cards: List<Card>) = withContext(Dispatchers.IO) {
        cardsLocalDataSource.insert(cards)
    }

    override suspend fun update(card: Card) = withContext(Dispatchers.IO) {
        wordCombinationLocalDataSource.update(card.wordCombination)
        cardsLocalDataSource.update(card)
    }

    override suspend fun deleteById(cardId: Long) = withContext(Dispatchers.IO) {
        cardsLocalDataSource.deleteById(cardId)
    }

    override suspend fun delete(card: Card) = withContext(Dispatchers.IO) {
        wordCombinationLocalDataSource.deleteById(card.wordCombination.wordCombinationId)
        cardsLocalDataSource.delete(card)
    }
}