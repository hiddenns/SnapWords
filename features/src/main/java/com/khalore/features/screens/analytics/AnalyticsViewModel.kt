package com.khalore.features.screens.analytics

import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.core.repository.cards.CardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val cardsRepository: CardsRepository,
    private val analyticsRepository: AnalyticsRepository
) : BaseViewModel<
        AnalyticsScreenContract.Event,
        AnalyticsScreenContract.State,
        AnalyticsScreenContract.Effect>() {

    init {
        setupAnalyticsState()
    }

    override fun setInitialState() = AnalyticsScreenContract.State(State.None)

    override fun handleEvents(event: AnalyticsScreenContract.Event) {
        when (event) {

            else -> {}
        }
    }

    private fun setupAnalyticsState() {
        viewModelScope.launch {
            val atomicTotalSwipes = AtomicInteger(0)
            val atomicTotalCards = AtomicInteger(0)
            val atomicAvgAddedCards = AtomicInteger(0)
            val atomicDaysInRow = AtomicInteger(0)

            val analyticsDailyList = mutableListOf<DailyAnalytic>()

            val jobs = listOf(
                viewModelScope.launch {

                },
                viewModelScope.launch {

                },
                viewModelScope.launch {

                },
                viewModelScope.launch {

                },
                viewModelScope.launch {

                }

            )

            jobs.joinAll()

            val textToNumberList = listOf(
                TextToNumberAnalyticsItem(message = "average", count = atomicAvgAddedCards.toLong()),
                TextToNumberAnalyticsItem(message = "total swipes", count = atomicTotalSwipes.toLong()),
                TextToNumberAnalyticsItem(message = "total cards", count = atomicTotalCards.toLong()),
                TextToNumberAnalyticsItem(message = "days in a row", count = atomicDaysInRow.toLong())
            )

            val event = AnalyticsScreenContract.Event.SetupAnalyticState(
                AnalyticsViewState(
                    textToNumberAnalyticsList = textToNumberList,
                    weekDailyAnalyticsList = emptyList()
                )
            )

        }
    }

}