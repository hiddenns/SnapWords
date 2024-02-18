package com.khalore.core.mappers

import com.khalore.core.entity.CardDTO
import com.khalore.core.entity.CardLocal
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.WordsCombination

fun CardLocal.toDomain(): Card {
    return Card(
        cardId = this@toDomain.cardId,
        wordCombination = WordsCombination(),
        rate = this@toDomain.rate
    )
}

fun Card.toLocal(): CardLocal {
    return CardLocal(
        wordCombinationId = this@toLocal.wordCombination.toLocal().wordCombinationId,
        rate = this@toLocal.rate,
        correctResponses = this@toLocal.correctResponses,
        incorrectResponses = this@toLocal.incorrectResponses,
        lastResponseDate = this@toLocal.lastResponseDate
    )
}

fun Card.toLocalWithId(): CardLocal {
    return CardLocal(
        cardId = this@toLocalWithId.cardId,
        wordCombinationId = this@toLocalWithId.wordCombination.toLocalWithId().wordCombinationId,
        rate = this@toLocalWithId.rate,
        correctResponses = this@toLocalWithId.correctResponses,
        incorrectResponses = this@toLocalWithId.incorrectResponses,
        lastResponseDate = this@toLocalWithId.lastResponseDate
    )
}

fun CardDTO.toDomain(): Card {
    return Card(
        cardId = this@toDomain.card.cardId,
        wordCombination = this@toDomain.wordCombination.toDomain(),
        rate = this@toDomain.card.rate,
        correctResponses = this@toDomain.card.correctResponses,
        incorrectResponses = this@toDomain.card.incorrectResponses,
        lastResponseDate = this@toDomain.card.lastResponseDate
    )
}