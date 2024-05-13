package com.khalore.features.screens.collection

import com.khalore.core.model.card.Card

data class CollectionViewState(
    val cardsList: List<Card>,
    val translateInputWord: String = ""
)