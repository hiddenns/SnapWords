package com.khalore.core.model.word

data class WordsCombination(
    val wordCombinationId: Long = -1,
    val word: String = "",
    val otherWord: String = "",
    val description: String? = null,
    val otherDescription: String? = null
)

data class Word(
    val word: String,
    val description: String?,
)

fun WordsCombination.getWord() = Word(
    word = this.word,
    description = this.description,
)

fun WordsCombination.getOtherWord() = Word(
    word = this.otherWord,
    description = this.otherDescription,
)
