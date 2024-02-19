package com.khalore.features.screens.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.core.model.card.Card


@Composable
fun CollectionScreen(
    viewModel: CollectionViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.value

    val onSaveCard = { card: Card ->
        viewModel.handleEvents(CollectionScreenContract.Event.AddCard(card))
    }

    val onRemoveCard = { card: Card ->
        viewModel.handleEvents(CollectionScreenContract.Event.DeleteCard(card))
    }

    val onUpdateCard = { card: Card ->
        viewModel.handleEvents(CollectionScreenContract.Event.UpdateCard(card))
    }

    CollectionScreenContent(
        viewState = viewState.state,
        onSaveCard = onSaveCard,
        onRemoveCard = onRemoveCard,
        onUpdateCard = onUpdateCard
    )
}

@Composable
fun CollectionScreenContent(
    viewState: State<CollectionViewState>,
    onSaveCard: (card: Card) -> Unit,
    onRemoveCard: (Card) -> Unit,
    onUpdateCard: (Card) -> Unit,
) {
    CollectionCardsDialog(
        viewState = viewState,
        onSaveCard = onSaveCard,
        onRemoveCard = onRemoveCard,
        onUpdateCard = onUpdateCard,
    )
}


@Preview(
    device = Devices.PIXEL_4
)
@Composable
fun EmptyCollection() {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val image: Painter =
                painterResource(id = androidx.appcompat.R.drawable.abc_star_black_48dp)
            Image(painter = image, contentDescription = "")
            Text(
                text = "Welcome to your Card Collection!",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.titleLarge,
                fontStyle = FontStyle.Normal
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                ,
            ) {

                Text(
                    text = "Here, you can build up your vocabulary and review the words you've already created.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Divider(
                    modifier = Modifier
                        .padding(
                            horizontal = 32.dp,
                            vertical = 8.dp
                        )
                        .fillMaxWidth()
                        .height(1.dp)
                )

                Text(
                    text = "Tap the \"Create Card\" button at the bottom right.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Divider(
                    modifier = Modifier
                        .padding(
                            horizontal = 32.dp,
                            vertical = 8.dp
                        )
                        .fillMaxWidth()
                        .height(1.dp)
                )

                Text(
                    text = "Delete a card by swiping left or right on the chosen word.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}