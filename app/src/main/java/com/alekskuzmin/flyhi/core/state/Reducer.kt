package com.alekskuzmin.flyhi.core.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alekskuzmin.flyhi.core.domain.FlightsResult

class Reducer {

    internal fun reduce(
        previousState: LiveData<AppState>,
        actionResult: LiveData<FlightsResult>
    ): LiveData<AppState> {
        return Transformations.map(actionResult) { flightsResult ->
            val appState = previousState.value
            return@map when (flightsResult) {
                is FlightsResult.Success -> {
                    val flightsToDisplay = flightsResult.data?.filter { flight ->
                        appState?.displayedDestinations == null || !appState.displayedDestinations.contains(
                            flight.mapIdto
                        )
                    }?.take(AppState.FLIGHTS_PER_DATE)
                    appState?.copy(
                        isLoading = false,
                        flights = flightsToDisplay,
                        error = null,
                        displayedDestinations = arrayListOf<String>().also { displayedFlights ->
                            flightsToDisplay?.let {
                                it.map { flight ->
                                    displayedFlights.add(
                                        flight.mapIdto
                                    )
                                }
                            }
                            appState.displayedDestinations?.let {
                                displayedFlights.addAll(
                                    it
                                )
                            }
                        }
                    )
                }
                is FlightsResult.Failure -> appState?.copy(
                    isLoading = false,
                    error = flightsResult.error,
                    flights = emptyList()
                )
                is FlightsResult.Loading -> {
                    appState?.copy(
                        isLoading = true,
                        selectedDate = flightsResult.selectedDate,
                        flights = emptyList(),
                        error = null
                    )
                }
            }
        }
    }

}