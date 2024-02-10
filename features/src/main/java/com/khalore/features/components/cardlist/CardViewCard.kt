package com.khalore.features.components.cardlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.khalore.core.model.card.Card
import com.khalore.features.components.CardShapeCanvas

@Composable
fun CardViewCard(card: Card, onRemove: (Card) -> Unit, onEdit: (Card) -> Unit) {
    val wordsCombination = card.wordCombination
    ListItem(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .padding(vertical = 4.dp),
        headlineContent = {
            Text(
                wordsCombination.word,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Divider(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Text(
                wordsCombination.otherWord,
                style = MaterialTheme.typography.titleSmall
            )
        },
        leadingContent = {
            CardShapeCanvas(card.getResponsesColor())
        },
        trailingContent = {
            Row {
                IconButton(
                    onClick = { onEdit(card) },
                ) {
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "edit card",
                    )
                }

                IconButton(
                    onClick = {
                        onRemove(card)
                    },
                ) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = "delete card"
                    )
                }
            }
        }
    )

}