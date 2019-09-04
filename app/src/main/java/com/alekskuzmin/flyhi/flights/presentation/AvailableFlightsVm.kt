package com.alekskuzmin.flyhi.flights.presentation

import com.alekskuzmin.flyhi.core.dispatcher.FlightsDispatcher
import com.alekskuzmin.flyhi.core.state.AppState
import com.alekskuzmin.flyhi.core.state.StateHolder
import com.alekskuzmin.flyhi.core.view.FlightsViewState
import com.alekskuzmin.flyhi.core.viewmodel.FlyHiViewModel

class AvailableFlightsVm(stateHolder: StateHolder, dispatcher: FlightsDispatcher) :
    FlyHiViewModel(stateHolder, dispatcher) {

    override fun preRender(appState: AppState): FlightsViewState? {
        return FlightsViewState(
            appState.isLoading,
            appState.flights?.map {
                FlightItem(
                    it.cityTo,
                    //TODO inject localization
                    "From ${it.cityFrom} for ${it.price} ${it.currency}",
                    "https://images.kiwi.com/photos/385x320/${it.mapIdto}.jpg",
                    it.deep_link
                )
            },
            appState.selectedDate,
            error = if (!appState.isLoading && appState.flights.isNullOrEmpty()) "No more unique flights!" else null
        )
    }

}
