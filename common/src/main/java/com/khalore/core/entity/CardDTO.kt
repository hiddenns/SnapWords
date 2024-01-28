package com.khalore.core.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CardDTO (

    @Embedded
    val card: CardLocal,

    @Relation(
        parentColumn = "wordCombinationId",
        entityColumn = "wordCombinationId"
    )
    val wordCombination: WordCombinationLocal
)