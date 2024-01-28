package com.khalore.features.screens.collection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    CollectionScreenContent(
        viewState = viewState.state,
        onSaveCard = onSaveCard,
        onRemoveCard = onRemoveCard
    )
}

@Composable
fun CollectionScreenContent(
    viewState: State<CollectionViewState>,
    onSaveCard: (card: Card) -> Unit,
    onRemoveCard: (Card) -> Unit
) {
    CreateCollectionCardsDialog(
        viewState = viewState,
        onSaveCard = onSaveCard,
        onRemoveCard = onRemoveCard
    )
}


@Preview
@Composable
fun EmptyCollection() {
    Box(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}
