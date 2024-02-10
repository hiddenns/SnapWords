package com.khalore.features.components.cardlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khalore.core.model.card.Card

@Composable
fun CollectionList(
    cardList: List<Card>,
    onRemoveCard: (Card) -> Unit
) {

    val openDeleteDialog = remember { mutableStateOf(false) }
    val swipedCard: MutableState<Card?> = remember { mutableStateOf(null) }

    val onRemoveSwipe: (Card) -> Unit = { card ->
        openDeleteDialog.value = true
        swipedCard.value = card
    }

    if (openDeleteDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDeleteDialog.value = false
            },
            title = {
                Text(text = "Remove card")
            },
            text = {
                Text(text = "Do you want to delete card?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDeleteDialog.value = false
                        swipedCard.value?.let { onRemoveCard(it) }
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDeleteDialog.value = false
                    }
                ) {
                    Text("No")
                }
            }
        )
    }

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
                ) { _, cardContent ->
                    // Display each email item
                    CardListItem(cardContent, onRemove = onRemoveSwipe, onEdit = {})
                }
            }
        }
    }
}