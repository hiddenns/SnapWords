package com.khalore.core.model.card

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.khalore.core.model.word.WordsCombination

data class Card(
    val cardId: Long = -1,
    val wordCombination: WordsCombination,
    val dateUtc: Long = 0,
    val rate: Long = 0,
    var correctResponses: Long = 0,
    var incorrectResponses: Long = 0,
    val lastResponseDate: Long = System.currentTimeMillis()
) {

    companion object {
        private const val LEARNED_ANSWERS_COUNT = 100
    }

    fun getResponsesColor(): Brush {
        Log.d("anal", "getResponsesColor: correctResponses $correctResponses , incorrectResponses $incorrectResponses")
        val maxCorrectResponses = 100f
        val saturationColorBoost = .3f
        val differenceResponses = kotlin.math.abs(correctResponses - incorrectResponses)
        val coefficient = differenceResponses.div(maxCorrectResponses)
        val hue = if (correctResponses > incorrectResponses) 125f else 0f
        val saturation = when {
            coefficient > 1 -> 1f
            coefficient != 0f && coefficient < saturationColorBoost -> saturationColorBoost
            else -> coefficient
        }
        return Brush.linearGradient(
            colors = listOf(
                Color.hsl(hue, saturation, .5f),
                Color.hsl(hue, saturation, .5f)
            ),
            start = Offset.Infinite,
            end = Offset.Infinite
        )

    }

    fun getProgressPercent(): Int {
        if (incorrectResponses >= correctResponses) return 0
        val diff = correctResponses - incorrectResponses

        if (correctResponses > LEARNED_ANSWERS_COUNT) return LEARNED_ANSWERS_COUNT
        val percent = diff * 100 / LEARNED_ANSWERS_COUNT
        return percent.toInt()
    }

    fun hasDescription() = wordCombination.description.isNullOrEmpty().not()

}
