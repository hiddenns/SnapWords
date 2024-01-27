package com.khalore.features.screens.home

import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.repository.CardsRepository
import com.khalore.features.components.cards.cardsColors
import com.khalore.features.components.cards.defaultCards
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseViewModel<
        HomeScreenContract.Event,
        HomeScreenContract.State,
        HomeScreenContract.Effect>() {

    override fun setInitialState() = setupDefaultState()

    override fun handleEvents(event: HomeScreenContract.Event) {

    }

    private fun setupDefaultState() = HomeScreenContract.State(
        State.Data(
            HomeViewState(
                cardsColors = cardsColors,
                cardsList = defaultCards
            )
        )
    )

}