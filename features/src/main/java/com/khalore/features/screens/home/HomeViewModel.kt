package com.khalore.features.screens.home

import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<
        HomeScreenContract.Event,
        HomeScreenContract.State,
        HomeScreenContract.Effect>() {

    val list = listOf("1", "2")
    override fun setInitialState(): HomeScreenContract.State = HomeScreenContract.State(State.None)

    override fun handleEvents(event: HomeScreenContract.Event) {
        TODO("Not yet implemented")
    }
}