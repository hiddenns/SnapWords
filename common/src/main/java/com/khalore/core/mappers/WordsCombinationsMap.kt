package com.khalore.core.mappers

import com.khalore.core.entity.WordCombinationLocal
import com.khalore.core.model.word.WordsCombination

fun WordCombinationLocal.toDomain(): WordsCombination {
    return WordsCombination(
        wordCombinationId = this@toDomain.wordCombinationId,
        word = this@toDomain.word,
        translate = this@toDomain.translate,
        description = this@toDomain.description,
        translateDescription = this@toDomain.translateDescription,
        language = this@toDomain.translate
    )
}

fun WordsCombination.toLocal(): WordCombinationLocal {
    return WordCombinationLocal(
        word = this@toLocal.word,
        translate = this@toLocal.translate,
        description = this@toLocal.description,
        translateDescription = this@toLocal.translateDescription,
        language = this@toLocal.translate
    )
}