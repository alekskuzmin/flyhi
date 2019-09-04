package com.alekskuzmin.flyhi.core.dispatcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.alekskuzmin.flyhi.core.domain.FlightsResult
import com.alekskuzmin.flyhi.core.domain.action.FlightsAction
import com.alekskuzmin.flyhi.core.remote.FlightsRepository

class FlightsDispatcher(private val repository: FlightsRepository) {

    private var lastAction: FlightsAction? = null

    val nextAction = MutableLiveData<FlightsAction>()

    fun dispatchedActionResult(action: FlightsAction) = liveData {
        lastAction = action
        when (action) {
            is FlightsAction.SelectDateAction -> {
                emit(FlightsResult.Loading(action.selectedDate))
                emit(
                    repository.getFlights(
                        action.selectedDate,
                        action.displayedFlightsCount,
                        action.flightsPerDate
                    )
                )
            }
        }
    }

    fun retryLastAction() {
        lastAction?.let { nextAction.value = it }
    }

}