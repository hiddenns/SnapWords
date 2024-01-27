package com.khalore.features.screens.collection

import com.khalore.core.base.BaseViewModel
import com.khalore.core.base.State
import com.khalore.core.repository.CardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val cardsRepository: CardsRepository
) : BaseViewModel<
        CollectionScreenContract.Event,
        CollectionScreenContract.State,
        CollectionScreenContract.Effect>() {

    override fun setInitialState() = CollectionScreenContract.State(State.None)

    override fun handleEvents(event: CollectionScreenContract.Event) {

    }
}