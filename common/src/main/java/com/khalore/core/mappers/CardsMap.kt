package com.khalore.core.mappers

import com.khalore.core.entity.CardLocal
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.WordsCombination

fun CardLocal.toDomain() : Card {
    return Card(
        cardId = this@toDomain.cardId,
        wordCombination = WordsCombination(),
        rate = this@toDomain.rate
    )
}

fun Card.toLocal(): CardLocal {
    return CardLocal(
        cardId = this@toLocal.cardId,
        wordCombinationId = this@toLocal.wordCombination.toLocal().wordCombinationId,
        rate = this@toLocal.rate
    )
}