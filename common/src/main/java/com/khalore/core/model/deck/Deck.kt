package com.khalore.core.model.deck

import com.khalore.core.model.card.Card

sealed class DeckAccessLevel() {
    data object Free : DeckAccessLevel()
    data object Ads : DeckAccessLevel()
    data object Purchase: DeckAccessLevel()
}

data class Deck(
    val deckId: Long,
    val name: String,
    val access: DeckAccessLevel,
    val isOwned: Boolean = false,
    val cards: List<Card>

)
