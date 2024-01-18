package com.khalore.features.screens.home

import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.features.components.swipable.cards.cardsColors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<
        HomeScreenContract.Event,
        HomeScreenContract.State,
        HomeScreenContract.Effect>() {

    override fun setInitialState(): HomeScreenContract.State = setupDefaultState()

    init {

    }

    override fun handleEvents(event: HomeScreenContract.Event) {
        TODO("Not yet implemented")
    }

    private fun setupDefaultState() = HomeScreenContract.State(
        State.Data(
            HomeViewState(
                cardsColors = cardsColors
            )
        )
    )

}