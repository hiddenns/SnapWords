package com.khalore.features.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.features.components.EmptyCardsScreen
import com.khalore.features.components.Error
import com.khalore.features.components.LoadingScreen
import com.khalore.features.components.cards.SwappableCards
import com.khalore.features.components.cards.SwippedCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickShop: () -> Unit = {},
    onClickCollection: () -> Unit = {},
    onClickInfo: () -> Unit = {}
) {
    val viewState = viewModel.viewState.value
    CardScreenContent(
        state = viewState.state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is HomeScreenContract.Effect.Navigation.ToCollectionScreen -> onClickCollection()
                is HomeScreenContract.Effect.Navigation.ToAnalyticScreen -> {}
                is HomeScreenContract.Effect.Navigation.ToInfoScreen -> onClickInfo()

            }
        }
    )
}

@Composable
fun CardScreenContent(
    state: State<HomeViewState>,
    effectFlow: Flow<HomeScreenContract.Effect>?,
    onEventSent: (event: HomeScreenContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: HomeScreenContract.Effect.Navigation) -> Unit,
) {
    val LAUNCH_LISTEN_FOR_EFFECTS = 12323123123
    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is HomeScreenContract.Effect.Navigation.ToCollectionScreen -> {
                    onNavigationRequested(effect)
                }

                is HomeScreenContract.Effect.Navigation.ToInfoScreen -> {
                    onNavigationRequested(effect)
                }

                is HomeScreenContract.Effect.Navigation.ToAnalyticScreen -> {
                    onNavigationRequested(effect)
                }
            }
        }?.collect()
    }

    when (state) {
        is State.Data -> SwappableCards(
            state.asData(),
            onSwippedCard = { swippedCardState: SwippedCardState ->
                onEventSent(HomeScreenContract.Event.SwipeCard(swippedCardState))
            })

        is State.Error -> Error()
        is State.Loading -> LoadingScreen()
        is State.None -> EmptyCardsScreen(
            onEventSent = onEventSent
        )
    }
}