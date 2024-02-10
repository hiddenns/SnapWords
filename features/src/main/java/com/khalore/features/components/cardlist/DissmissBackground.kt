package com.khalore.features.components.cardlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color(0xFFFF1744)
        DismissDirection.EndToStart -> Color(0xFFFF1744)
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxSize()
            .background(color)
            .padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.StartToEnd) Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart) Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
    }
}