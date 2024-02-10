package com.khalore.core.datasource.cards

import com.khalore.core.dao.CardDao
import com.khalore.core.entity.CardLocal
import com.khalore.core.mappers.toDomain
import com.khalore.core.mappers.toLocal
import com.khalore.core.mappers.toLocalWithId
import com.khalore.core.model.card.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardsLocalDataSourceImpl @Inject constructor(
    private val cardsDao: CardDao
) : CardsLocalDataSource {

    override fun getCardsFlow(): Flow<List<Card>> {
        return cardsDao.getCardsFlow().map { cards ->
            cards.map {
                it.toDomain()
            }
        }
    }

    override fun getCardByIdFlow(cardId: Long): Flow<Card> {
        return cardsDao.getCardByIdFlow(cardId).map { cards ->
            cards.toDomain()
        }
    }

    override suspend fun insert(card: CardLocal) =
        cardsDao.insert(card)

    override suspend fun insert(cards: List<Card>) {
        cardsDao.insert(cards.map {
            it.toLocal()
        })
    }

    override suspend fun update(card: Card) {
        cardsDao.update(card.toLocalWithId())
    }

    override suspend fun deleteById(cardId: Long) {
        cardsDao.deleteById(cardId)
    }

    override suspend fun delete(card: Card) {
        cardsDao.delete(card.toLocal())
    }

}