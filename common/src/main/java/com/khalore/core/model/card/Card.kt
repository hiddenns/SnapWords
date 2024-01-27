package com.khalore.core.model.card

import com.khalore.core.model.word.WordsCombination

data class Card(
    val cardId: Long,
    val wordCombination: WordsCombination,
    val rate: Long
)
