package com.khalore.features.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.features.components.swipable.cards.SwappableCards

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    SwappableCards(viewModel.list)
}