package com.khalore.features.screens.analytics

import androidx.lifecycle.viewModelScope
import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.model.analytics.DailyAnalytic
import com.khalore.core.repository.analytics.AnalyticsRepository
import com.khalore.snapwords.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
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

            val analyticsDailyList = mutableListOf<DailyAnalytic>()

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
                    message = "Total swipes",
                    count = atomicTotalSwipes.toLong(),
                    icon = R.drawable.ic_hand_cursor
                ),
                TextToNumberAnalyticsItem(
                    message = "Total cards",
                    count = atomicTotalCards.toLong(),
                    icon = R.drawable.ic_idea
                ),
                TextToNumberAnalyticsItem(
                    message = "Average swipes",
                    count = atomicAvgAddedCards.toLong(),
                    icon = R.drawable.ic_tinder
                ),
                TextToNumberAnalyticsItem(
                    message = "Swipes days in a row",
                    count = atomicDaysInRow.toLong(),
                    icon = R.drawable.ic_clock
                )
            )

            val event = AnalyticsScreenContract.Event.SetupAnalyticState(
                AnalyticsViewState(
                    textToNumberAnalyticsList = textToNumberList,
                    weekDailyAnalyticsList = analyticsDailyList.toList()
                )
            )

            handleEvents(event)

        }
    }

}