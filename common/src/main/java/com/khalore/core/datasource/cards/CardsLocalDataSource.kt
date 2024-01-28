package com.khalore.core.datasource.cards

import com.khalore.core.entity.CardLocal
import com.khalore.core.model.card.Card
import kotlinx.coroutines.flow.Flow

interface CardsLocalDataSource {

    fun getCardsFlow(): Flow<List<Card>>

    fun getCardByIdFlow(cardId: Long): Flow<Card>

    suspend fun insert(card: CardLocal): Long

    fun insert(cards: List<Card>)

    fun update(card: Card)

    suspend fun deleteById(cardId: Long)

    suspend fun delete(card: Card)
}