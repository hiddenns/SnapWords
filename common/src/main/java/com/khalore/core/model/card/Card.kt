package com.khalore.core.model.card

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.khalore.core.model.word.WordsCombination

data class Card(
    val cardId: Long = -1,
    val wordCombination: WordsCombination,
    val rate: Long,
    var correctResponses: Long = 0,
    var incorrectResponses: Long = 0,
    val lastResponseDate: Long = System.currentTimeMillis()
) {

    fun getResponsesColor(): Brush {
        val maxCorrectResponses = 100f

        val saturationColorBoost = .3f

        val isCorrectMore = correctResponses > incorrectResponses
        val differenceResponses = if (isCorrectMore) {
            correctResponses - incorrectResponses
        } else {
            incorrectResponses - correctResponses
        }

        val coefficient: Float = differenceResponses.div(maxCorrectResponses)

        val saturation = if (coefficient > 1) {
            1f
        } else if (coefficient != 0f && coefficient < saturationColorBoost) {
            saturationColorBoost
        } else {
            coefficient
        }

        val hue = if (isCorrectMore) 125f else 0f

        return Brush.linearGradient(
            colors = listOf(
                Color.hsl(hue, saturation, .5f),
                Color.hsl(hue, saturation, .5f)
            ),
            start = Offset.Infinite,
            end = Offset.Infinite
        )

    }

}
