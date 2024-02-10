package com.khalore.core.repository

import com.khalore.core.model.card.Card
import kotlinx.coroutines.flow.Flow

interface CardsRepository {

    fun getCardsFlow(): Flow<List<Card>>

    fun getCardByIdFlow(cardId: Long): Flow<Card>

    suspend fun insert(card: Card)

    suspend fun insert(cards: List<Card>)

    suspend fun update(card: Card)

    suspend fun deleteById(cardId: Long)

    suspend fun delete(card: Card)
}