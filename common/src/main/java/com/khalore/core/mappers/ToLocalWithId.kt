package com.khalore.core.mappers

import com.khalore.core.entity.CardLocal
import com.khalore.core.model.card.Card

fun Card.toLocalWithId(): CardLocal {
    return CardLocal(
        cardId = this@toLocalWithId.cardId,
        wordCombinationId = this@toLocalWithId.wordCombination.toLocalWithId().wordCombinationId,
        rate = this@toLocalWithId.rate,
        correctResponses = this@toLocalWithId.correctResponses,
        incorrectResponses = this@toLocalWithId.incorrectResponses,
        lastResponseDate = this@toLocalWithId.lastResponseDate,
        dayUtc = 0
    )
}