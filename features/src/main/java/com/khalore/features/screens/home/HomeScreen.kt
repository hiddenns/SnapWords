package com.khalore.features.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.features.components.Error
import com.khalore.features.components.cards.SwappableCards

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState.value
    CardScreenContent(viewState.state)
}

@Composable
fun CardScreenContent(state: State<HomeViewState>) {
    when (state) {
        is State.Data -> SwappableCards(state.asData())
        is State.Error -> Error()
        is State.Loading -> Error()
        is State.None -> Error()
    }
}