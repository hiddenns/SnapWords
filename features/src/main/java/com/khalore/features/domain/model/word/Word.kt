package com.khalore.features.domain.model.word

data class WordsCombination(
    val id: Long,
    val word: String,
    val translate: String,
    val description: String? = null,
    val translateDescription: String? = null,
    val language: String
)

data class Word(
    val word: String,
    val description: String?,
)

fun WordsCombination.getWord() = Word(
    word = this.word,
    description = this.description,
)

fun WordsCombination.getTranslate() = Word(
    word = this.translate,
    description = this.translateDescription,
)
