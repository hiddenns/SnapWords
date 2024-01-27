package com.khalore.core.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.WordsCombination

data class CardDTO (

    @Embedded
    val card: Card,

    @Relation(
        parentColumn = "wordsCombinationId",
        entityColumn = "cardId",
        associateBy = Junction(WordsCombination::class)
    )
    val wordsCombination: WordsCombination,
)