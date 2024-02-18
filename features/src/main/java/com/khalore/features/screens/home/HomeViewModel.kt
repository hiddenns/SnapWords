package com.khalore.features.screens.home

import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.model.card.Card
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
        fetchCards()
    }

    override fun setInitialState() = HomeScreenContract.State(State.Loading)

    override fun handleEvents(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.SetupCards -> {
                setupCards(event.cardList)
            }
            is HomeScreenContract.Event.SwipeCard -> {
                viewModelScope.launch {
                    val updatedCard = event.swippedCardState.card.apply {
                        if (event.swippedCardState.isPositiveAnswer) {
                            this.correctResponses++
                        } else {
                            this.incorrectResponses++
                        }
                    }.copy(lastResponseDate = System.currentTimeMillis())

                    cardsRepository.update(updatedCard)
                }
            }
        }
    }

    private fun setupCards(cards: List<Card>) {
        if (cards.isEmpty()) {
            setState { HomeScreenContract.State(State.None) }
            return
        }
        setState {
            HomeScreenContract.State(
                State.Data(
                    HomeViewState(
                        cardsColors = cardsColors,
                        cardsList = cards
                    )
                )
            )
        }
    }

    private fun fetchCards() {
        viewModelScope.launch {
            val cards = cardsRepository.getCardsFlow().firstOrNull()
            handleEvents(
                HomeScreenContract.Event.SetupCards(
                    if (cards.isNullOrEmpty())
                        emptyList()
                    else cards
                )
            )
        }
    }
}