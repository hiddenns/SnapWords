package com.khalore.features.domain.model.card

import com.khalore.features.domain.model.word.WordsCombination

data class Card(
    val cardId: Long,
    val wordCombination: WordsCombination
)
