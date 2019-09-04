package com.alekskuzmin.flyhi.core.view

import com.alekskuzmin.flyhi.core.domain.model.SimpleDate
import com.alekskuzmin.flyhi.flights.presentation.FlightItem

class FlightsViewState(
    val loading: Boolean,
    val flights: List<FlightItem>?,
    val selectedDate: SimpleDate,
    override var error: String? = null
) : BaseViewState()
