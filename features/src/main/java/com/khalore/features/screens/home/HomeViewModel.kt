package com.khalore.features.screens.home

import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import com.khalore.core.analyticmanager.AnalyticManager
import com.khalore.core.analyticmanager.AnalyticsEvents
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.model.card.Card
import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.core.repository.cards.CardsRepository
import com.khalore.features.components.cards.SwippedCardState
import com.khalore.features.components.cards.cardsColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cardsRepository: CardsRepository,
    private val analyticsRepository: AnalyticsRepository,
    private val analyticManager: AnalyticManager
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
                    sendSwipeAnalyticEvent(event.swippedCardState)
                    //saveSwipedCardUpdate(event.swippedCardState)
                    saveDailyAnalytics(event.swippedCardState)
                }
            }

            is HomeScreenContract.Event.RotateCard -> {
                sendRotateCardAnalyticEvent()
            }

            is HomeScreenContract.Event.Navigation.ToCollectionScreen -> {
                analyticManager.logEvent(AnalyticsEvents.NAVIGATE_TO_COLLECTION_FROM_HOME_EMPTY_SCREEN)
                setEffect { HomeScreenContract.Effect.Navigation.ToCollectionScreen }
            }
            is HomeScreenContract.Event.Navigation.ToInfoScreen -> {
                analyticManager.logEvent(AnalyticsEvents.NAVIGATE_TO_INFO_FROM_HOME_EMPTY_SCREEN)
                setEffect { HomeScreenContract.Effect.Navigation.ToInfoScreen }
            }
            is HomeScreenContract.Event.Navigation.ToAnalyticScreen -> {
                setEffect { HomeScreenContract.Effect.Navigation.ToAnalyticScreen }
            }
        }
    }


    private fun saveDailyAnalytics(swippedCardState: SwippedCardState) {
        viewModelScope.launch {
            analyticsRepository.increaseSwipesCount(
                swippedCardState.card,
                swippedCardState.isPositiveAnswer
            )
        }
    }

    private fun saveSwipedCardUpdate(swippedCardState: SwippedCardState) {
        viewModelScope.launch {
            val updatedCard = swippedCardState.card.apply {
                if (swippedCardState.isPositiveAnswer) {
                    this.correctResponses++
                } else {
                    this.incorrectResponses++
                }
            }.copy(lastResponseDate = System.currentTimeMillis())

            cardsRepository.update(updatedCard)

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

    private fun sendSwipeAnalyticEvent(swipedCardState: SwippedCardState) {
        val swipeAnalyticBundle = bundleOf(
            "isPositive" to swipedCardState.isPositiveAnswer
        )
        analyticManager.logEvent(AnalyticsEvents.CARD_SWIPE, swipeAnalyticBundle)
    }

    private fun sendRotateCardAnalyticEvent() {
        analyticManager.logEvent(AnalyticsEvents.ROTATE_CARD)
    }

}