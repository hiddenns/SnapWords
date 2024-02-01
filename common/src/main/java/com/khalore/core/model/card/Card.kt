package com.khalore.core.model.card

import com.khalore.core.model.word.WordsCombination

enum class LanguageLevel {
    A1, A2, B1, B2, C1, C2
}

data class Card(
    val cardId: Long = -1,
    val wordCombination: WordsCombination,
    val level: LanguageLevel? = null,
    val rate: Long,
    val correctResponses: Long = 0,
    val incorrectResponses: Long = 0,
    val lastResponseDate: Long = System.currentTimeMillis()
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
                rate = 0,
                level = null,
            )
        }
    }
}
