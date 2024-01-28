package com.khalore.features.components.cardlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khalore.core.model.card.Card

@Composable
fun CollectionList(
    cardList: List<Card>,
    onRemoveCard: (Card) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
            ) {
                itemsIndexed(
                    items = cardList,
                    // Provide a unique key based on the email content
                    key = { _, item -> item.cardId }
                ) { _, emailContent ->
                    // Display each email item
                    CardListItem(emailContent, onRemove = onRemoveCard)
                }
            }
        }
    }
}