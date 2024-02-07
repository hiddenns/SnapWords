package com.khalore.core.model.card

import com.khalore.core.model.word.WordsCombination

data class Card(
    val cardId: Long = -1,
    val wordCombination: WordsCombination,
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
                    otherWord = "The other side",
                    description = "Description",
                    otherDescription = "Description"
                ),
                rate = 0,
            )
        }
    }
}
