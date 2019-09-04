package com.alekskuzmin.flyhi.core.domain

import com.alekskuzmin.flyhi.core.domain.model.SimpleDate
import com.alekskuzmin.flyhi.core.domain.result.Result
import com.alekskuzmin.flyhi.core.remote.model.Flight

sealed class FlightsResult : Result {
    data class Loading(val selectedDate: SimpleDate) : FlightsResult()
    data class Success(val data: List<Flight>?) : FlightsResult()
    data class Failure(val error: String) : FlightsResult()
}