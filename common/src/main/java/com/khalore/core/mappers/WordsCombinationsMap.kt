package com.khalore.core.mappers

import com.khalore.core.entity.WordCombinationLocal
import com.khalore.core.model.word.WordsCombination

fun WordCombinationLocal.toDomain(): WordsCombination {
    return WordsCombination(
        wordCombinationId = this@toDomain.wordCombinationId,
        word = this@toDomain.word,
        otherWord = this@toDomain.otherWords,
        description = this@toDomain.description,
        otherDescription = this@toDomain.otherDescription,
    )
}

fun WordsCombination.toLocal(): WordCombinationLocal {
    return WordCombinationLocal(
        word = this@toLocal.word,
        otherWords = this@toLocal.otherWord,
        description = this@toLocal.description,
        otherDescription = this@toLocal.otherDescription,
    )
}

fun WordsCombination.toLocalWithId(): WordCombinationLocal {
    return WordCombinationLocal(
        wordCombinationId = this@toLocalWithId.wordCombinationId,
        word = this@toLocalWithId.word,
        otherWords = this@toLocalWithId.otherWord,
        description = this@toLocalWithId.description,
        otherDescription = this@toLocalWithId.otherDescription,
    )
}