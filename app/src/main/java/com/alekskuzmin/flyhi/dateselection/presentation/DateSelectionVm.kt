package com.alekskuzmin.flyhi.dateselection.presentation

import com.alekskuzmin.flyhi.core.dispatcher.FlightsDispatcher
import com.alekskuzmin.flyhi.core.domain.action.FlightsAction
import com.alekskuzmin.flyhi.core.domain.model.SimpleDate
import com.alekskuzmin.flyhi.core.state.AppState
import com.alekskuzmin.flyhi.core.state.StateHolder
import com.alekskuzmin.flyhi.core.view.FlightsViewState
import com.alekskuzmin.flyhi.core.viewmodel.FlyHiViewModel

class DateSelectionVm(dispatcher: FlightsDispatcher, stateHolder: StateHolder) :
    FlyHiViewModel(stateHolder, dispatcher) {

    override fun preRender(appState: AppState): FlightsViewState? {
        return FlightsViewState(
            false,
            null,
            appState.selectedDate
        )
    }

    fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val selectedDate = SimpleDate(year, monthOfYear, dayOfMonth)
        val currentState = stateHolder.getAppState().value
        val previousDate = currentState?.selectedDate
        if (previousDate != selectedDate || stateHolder.isUninitialized()) {
            dispatcher.nextAction.value =
                FlightsAction.SelectDateAction(
                    selectedDate,
                    displayedFlightsCount = currentState?.displayedDestinations?.count()
                        ?: 0,
                    flightsPerDate = AppState.FLIGHTS_PER_DATE
                )
        }
    }

}
