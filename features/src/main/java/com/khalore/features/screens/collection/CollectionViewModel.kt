package com.khalore.features.screens.collection

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.model.card.Card
import com.khalore.core.repository.CardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseViewModel<
        CollectionScreenContract.Event,
        CollectionScreenContract.State,
        CollectionScreenContract.Effect>() {

    init {
        fetchCards()
    }

    override fun setInitialState() = CollectionScreenContract.State(State.None)

    override fun handleEvents(event: CollectionScreenContract.Event) {
        when(event) {
            is CollectionScreenContract.Event.AddCard -> addCard(event.card)
            is CollectionScreenContract.Event.DeleteCard -> deleteCard(event.card)
            is CollectionScreenContract.Event.SetupCards -> setupCards(event.cards)
        }
    }

    private fun fetchCards() {
        viewModelScope.launch {
            val cards = cardsRepository.getCardsFlow().firstOrNull()
            handleEvents(
                CollectionScreenContract.Event.SetupCards(
                    if (cards.isNullOrEmpty())
                        emptyList()
                    else
                        cards
                )
            )
        }
    }

    private fun addCard(card: Card) {
        viewModelScope.launch {
            cardsRepository.insert(card)
        }
    }

    private fun deleteCard(card: Card) {
        viewModelScope.launch {
            Log.d("anal", "deleteCard: $card")
            cardsRepository.delete(card)
        }
    }

    private fun setupCards(cards: List<Card>) {
        viewModelScope.launch {
            if (cards.isEmpty()) {
                setState { CollectionScreenContract.State(State.None) }
                return@launch
            }
            setState {
                CollectionScreenContract.State(
                    State.Data(
                        CollectionViewState(
                            cardsList = cards
                        )
                    )
                )
            }
        }
    }
}