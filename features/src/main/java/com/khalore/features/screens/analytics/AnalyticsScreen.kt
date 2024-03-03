package com.khalore.features.screens.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.features.components.Error
import com.khalore.features.components.LoadingScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun AnalyticsScreen(
    viewModel: AnalyticsViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.value
    AnalyticsContent(
        state = viewState.state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->

        }
    )
}

@Composable
fun AnalyticsContent(
    state: State<AnalyticsViewState>,
    effectFlow: Flow<AnalyticsScreenContract.Effect>,
    onEventSent: (event: AnalyticsScreenContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: AnalyticsScreenContract.Effect.Navigation) -> Unit,
) {
    val LAUNCH_LISTEN_FOR_EFFECTS = 34543534
    // Listen for side effects from the VM
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
//                is HomeScreenContract.Effect.ToastDataWasLoaded -> { ... }
                else -> {}
            }
        }?.collect()
    }

    when (state) {
        is State.Data -> AnalyticsScreenMainContent(state.asData())
        is State.Error -> Error()
        is State.Loading -> LoadingScreen()
        is State.None -> LoadingScreen()
    }
}

