package com.khalore.features.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.repository.CardsRepository
import com.khalore.features.components.cards.cardsColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseViewModel<
        HomeScreenContract.Event,
        HomeScreenContract.State,
        HomeScreenContract.Effect>() {

    init {
        setupDefaultState()
    }

    override fun setInitialState() = HomeScreenContract.State(State.Loading)

    override fun handleEvents(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.SetupCards -> {
                if (event.cardList.isEmpty()) {
                    setState {
                        HomeScreenContract.State(State.None)
                    }
                    return
                }

                Log.d("anal", "handleEvents data: ${event.cardList}")
                setState {
                    HomeScreenContract.State(
                        State.Data(
                            HomeViewState(
                                cardsColors = cardsColors,
                                cardsList = event.cardList
                            )
                        )
                    )
                }
            }
        }
    }

    private fun setupDefaultState() {
        viewModelScope.launch {
            val cards = cardsRepository.getCardsFlow().firstOrNull()
            handleEvents(
                if (cards.isNullOrEmpty()) {
                    HomeScreenContract.Event.SetupCards(emptyList())
                } else {
                    HomeScreenContract.Event.SetupCards(cards)
                }
            )
        }
    }
}