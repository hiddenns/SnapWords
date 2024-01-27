package com.khalore.features.screens.collection.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.WordsCombination

class CollectionPreview : PreviewParameterProvider<List<Card>> {
    override val values: Sequence<List<Card>> = sequenceOf(
        listOf(
            Card(
                cardId = 1,
                wordCombination = WordsCombination(
                    wordCombinationId = 1,
                    word = "Word super long word long",
                    translate = "Translate Translate Translate",
                    description = "Translate Translate TranslateTranslate Translate",
                    translateDescription = null,
                    language = "eng"
                ),
                rate = 1
            ),
        )
    )
}
