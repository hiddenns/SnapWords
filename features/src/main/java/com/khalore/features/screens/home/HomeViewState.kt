package com.khalore.features.screens.home

import androidx.compose.ui.graphics.Color
import com.khalore.features.domain.model.card.Card

data class HomeViewState(
    val cardsColors: List<Color>,
    val cardsList: List<Card>
)