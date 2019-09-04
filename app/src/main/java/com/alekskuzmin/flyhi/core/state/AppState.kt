package com.alekskuzmin.flyhi.core.state

import com.alekskuzmin.flyhi.core.domain.model.SimpleDate
import com.alekskuzmin.flyhi.core.remote.model.Flight
import java.io.Serializable
import java.util.*

data class AppState(
    val isLoading: Boolean = false,
    val flights: List<Flight>? = emptyList(),
    val selectedDate: SimpleDate,
    val error: String? = null,
    val displayedDestinations: List<String>? = emptyList()
) : Serializable {

    companion object {
        //TODO: might be a part of immutable state to be modified in app settings screen
        const val FLIGHTS_PER_DATE: Int = 5

        fun getInitialState(): AppState {
            val calendar = Calendar.getInstance()
            return AppState(
                isLoading = false,
                flights = emptyList(),
                selectedDate = SimpleDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ),
                //TODO introduce sealed class to get rid of disambiguation between null and empty
                error = null,
                displayedDestinations = emptyList()
            )
        }
    }
}