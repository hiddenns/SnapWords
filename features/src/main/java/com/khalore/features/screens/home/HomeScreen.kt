package com.khalore.features.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.features.components.EmptyCardsScreen
import com.khalore.features.components.Error
import com.khalore.features.components.cards.SwappableCards

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickShop: () -> Unit = {},
    onClickCollection: () -> Unit = {},
) {
    val viewState = viewModel.viewState.value
    CardScreenContent(
        state = viewState.state,
        onClickShop = onClickShop,
        onClickCollection = onClickCollection
    )
}

@Composable
fun CardScreenContent(
    state: State<HomeViewState>,
    onClickShop: () -> Unit = {},
    onClickCollection: () -> Unit = {},
) {
    when (state) {
        is State.Data -> {
            Log.d("anal", "CardScreenContent: ${state.asData().cardsList}")
            SwappableCards(state.asData())
        }
        is State.Error -> Error()
        is State.Loading -> Error()
        is State.None -> EmptyCardsScreen(
            onClickShop = onClickShop,
            onClickCollection = onClickCollection
        )
    }
}