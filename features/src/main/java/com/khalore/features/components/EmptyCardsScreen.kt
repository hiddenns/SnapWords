package com.khalore.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun EmptyCardsScreen(
    onClickShop: () -> Unit = {},
    onClickCollection: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeEmptyCard(onClickShop)
            CreateUnicCard(onClickCollection)
        }
    }
}

@Composable
fun WelcomeEmptyCard(
    onClickShop: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .background(color = Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "It looks like you don't have any cards yet. Start your collection by adding a free card pack from the store!",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            FilledTonalButton(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = onClickShop
            ) {
                Text(
                    text = "Add free cards",
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun CreateUnicCard(
    onClickCollection: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
            .background(color = Color.Gray)
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "To create your unique card, navigate to the \"Collections\" section.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            OutlinedButton(
                modifier = Modifier
                    .padding(top = 12.dp),
                onClick = onClickCollection
            ) {
                Text(
                    text = "Collection",
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}