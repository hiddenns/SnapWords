package com.khalore.core.entity.deck

import androidx.room.Entity

@Entity(primaryKeys = ["deckId", "cardId"])
data class DeckWithCardLocal(
    val deckId: Long,
    val cardId: Long
)