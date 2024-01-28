package com.khalore.core.model.card

import com.khalore.core.model.word.WordsCombination

data class Card(
    val cardId: Long = -1,
    val wordCombination: WordsCombination,
    val rate: Long
) {
    companion object {
        fun default(): Card {
            return Card(
                wordCombination = WordsCombination(
                    word = "Word",
                    translate = "Translate",
                    description = "Description",
                    translateDescription = "Translate description"
                ),
                rate = 0
            )
        }
    }
}
