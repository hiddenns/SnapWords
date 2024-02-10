package com.khalore.features.components.cardlist

import androidx.compose.runtime.Composable
import com.khalore.core.model.card.Card


@Composable
fun CardListItem(
    card: Card,
    onRemove: (Card) -> Unit,
    onEdit: (Card) -> Unit,
) {
    CardViewCard(card, onRemove, onEdit)
}