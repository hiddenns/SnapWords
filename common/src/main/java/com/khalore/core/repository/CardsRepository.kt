package com.khalore.core.repository

import com.khalore.core.model.card.Card
import kotlinx.coroutines.flow.Flow

interface CardsRepository {

    fun getCardsFlow(): Flow<List<Card>>

    fun getCardByIdFlow(cardId: Long): Flow<Card>

    fun insert(card: Card)

    fun insert(cards: List<Card>)

    fun update(card: Card)

    fun deleteById(cardId: Long)

    fun delete(card: Card)
}