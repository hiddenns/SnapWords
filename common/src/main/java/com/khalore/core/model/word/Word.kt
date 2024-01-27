package com.khalore.core.model.word

import java.util.Locale

data class WordsCombination(
    val wordCombinationId: Long = -1,
    val word: String = "",
    val translate: String = "",
    val description: String? = null,
    val translateDescription: String? = null,
    val language: String = Locale.ENGLISH.language
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
