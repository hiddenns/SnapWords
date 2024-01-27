package com.khalore.features.screens.collection

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.features.components.CreateCardDialog
import com.khalore.features.components.Error
import com.khalore.features.screens.collection.preview.CollectionPreview


@Composable
fun CollectionScreen(
    viewModel: CollectionViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.value
    CollectionScreenContent(
        viewState = viewState.state
    )
}

@Composable
fun CollectionScreenContent(
    viewState: State<CollectionViewState>
) {
    when (viewState) {
        is State.Data -> CollectionList()
        is State.Error -> Error()
        is State.Loading -> Error()
        is State.None -> CreateCardDialog()
    }
}

@Preview
@PreviewParameter(CollectionPreview::class)
@Composable
fun CollectionList() {
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
