package com.khalore.features.components.cardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.khalore.core.model.word.WordsCombination

@Composable
fun CardViewCard(wordsCombination: WordsCombination) {
    ListItem(
        modifier = Modifier.clip(MaterialTheme.shapes.small),
        headlineContent = {
            Text(
                wordsCombination.word,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                wordsCombination.translate,
                style = MaterialTheme.typography.bodySmall
            )
        },
        leadingContent = {
            Icon(
                Icons.Default.Email,
                contentDescription = "person icon",
                Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(10.dp)
            )
        }
    )
}