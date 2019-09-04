package com.alekskuzmin.flyhi.core.domain.action

import com.alekskuzmin.flyhi.core.domain.model.SimpleDate

sealed class FlightsAction : Action {
    data class SelectDateAction(
        val selectedDate: SimpleDate,
        val displayedFlightsCount: Int,
        val flightsPerDate: Int
    ) : FlightsAction()
}