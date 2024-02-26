package com.khalore.features.screens.analytics

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.ext.getDate
import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.core.repository.cards.CardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong
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
            is AnalyticsScreenContract.Event.SetupAnalyticState -> {
                setState {
                    AnalyticsScreenContract.State(
                        State.Data(
                            AnalyticsViewState(
                                textToNumberAnalyticsList = event.state.textToNumberAnalyticsList,
                                weekDailyAnalyticsList = event.state.weekDailyAnalyticsList
                            )
                        )
                    )
                }
            }


            else -> {}
        }
    }

    private fun setupAnalyticsState() {
        viewModelScope.launch {
            val atomicTotalSwipes = AtomicLong(0)
            val atomicTotalCards = AtomicLong(0)
            val atomicAvgAddedCards = AtomicLong(0)
            val atomicDaysInRow = AtomicLong(0)

            var analyticsDailyList = mutableListOf<DailyAnalytic?>()

            val jobs = listOf(
                viewModelScope.launch(Dispatchers.IO) {
                    atomicTotalSwipes.set(analyticsRepository.getTotalSwipes())
                },
                viewModelScope.launch(Dispatchers.IO) {
                    atomicTotalCards.set(analyticsRepository.getTotalCards())
                },
                viewModelScope.launch(Dispatchers.IO) {
                    atomicAvgAddedCards.set(analyticsRepository.getAverageSwipesPerDays())
                },
                viewModelScope.launch(Dispatchers.IO) {
                    atomicDaysInRow.set(analyticsRepository.getSwipesDaysInRow())
                },
                viewModelScope.launch(Dispatchers.IO) {
                    analyticsDailyList.addAll(analyticsRepository.getWeekDailyAnalytics())
                }
            )

            jobs.joinAll()

            val textToNumberList = listOf(
                TextToNumberAnalyticsItem(
                    message = "average",
                    count = atomicAvgAddedCards.toLong()
                ),
                TextToNumberAnalyticsItem(
                    message = "total swipes",
                    count = atomicTotalSwipes.toLong()
                ),
                TextToNumberAnalyticsItem(
                    message = "total cards",
                    count = atomicTotalCards.toLong()
                ),
                TextToNumberAnalyticsItem(
                    message = "days in a row",
                    count = atomicDaysInRow.toLong()
                )
            )

            Log.d("anal", "setupAnalyticsState: $textToNumberList")

            analyticsDailyList.forEachIndexed { i, analytic ->
                Log.d("anal", "setupAnalyticsState: $i - ${analytic?.dayUtc?.getDate()}")
            }

            val event = AnalyticsScreenContract.Event.SetupAnalyticState(
                AnalyticsViewState(
                    textToNumberAnalyticsList = textToNumberList,
                    weekDailyAnalyticsList = emptyList()
                )
            )

            handleEvents(event)

        }
    }

}