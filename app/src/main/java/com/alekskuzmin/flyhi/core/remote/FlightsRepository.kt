package com.alekskuzmin.flyhi.core.remote

import com.alekskuzmin.flyhi.core.domain.FlightsResult
import com.alekskuzmin.flyhi.core.domain.model.SimpleDate
import com.alekskuzmin.flyhi.core.remote.model.Flight
import java.nio.charset.Charset

class FlightsRepository constructor(private val serviceProvider: ServiceProvider) {

    suspend fun getFlights(
        selectedDate: SimpleDate,
        displayedFlightsCount: Int,
        flightsPerDate: Int
    ): FlightsResult {
        abortFetch()
        val response =
            getFlights(selectedDate.toString(), displayedFlightsCount + 5 * flightsPerDate)
        return if (response is NetworkResult.Success) {
            FlightsResult.Success(response.data)
        } else {
            FlightsResult.Failure(response.toString())
        }
    }

    private suspend fun getFlights(date: String, limit: Int): NetworkResult<List<Flight>?> {
        val response = serviceProvider.service.getFlightsAsync(date, limit).await()
        if (response.isSuccessful) {
            val flightsResponse = response.body()
            val data = flightsResponse?.data
            return if (data.isNullOrEmpty()) {
                NetworkResult.Failure(
                    "Failed to retrieve result."
                )
            } else {
                data.forEach { flight -> flight.currency = flightsResponse.currency }
                NetworkResult.Success(data)
            }
        } else {
            return NetworkResult.Failure(
                response.errorBody()?.bytes()?.toString(Charset.forName("UTF-8"))
                    ?: "Failed to retrieve result."
            )
        }
    }

    private fun abortFetch() {
        serviceProvider.cancelAllRequests()
    }
}