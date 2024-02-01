package com.khalore.core.entity.deck

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.khalore.core.entity.CardLocal

data class DeckWithCardsDTO(
    @Embedded
    val deckLocal: DeckLocal,
    @Relation(
        parentColumn = "deckId",
        entity = CardLocal::class,
        entityColumn = "cardId",
        associateBy = Junction(
            value = DeckWithCardLocal::class,
            parentColumn = "deckId",
            entityColumn = "cardId"
        )
    )
    var cards: List<CardLocal>
)
