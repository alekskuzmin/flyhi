package com.alekskuzmin.flyhi.core.remote

import com.alekskuzmin.flyhi.core.remote.model.FlightsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlyHiService {

    @GET("flights?v=2&sort=popularity&asc=0&locale=en&children=0&infants=0&flyFrom=49.2-16.61-250km&to=anywhere&featureName=aggregateResults&typeFlight=oneway&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&partner=picky")
    fun getFlightsAsync(
        @Query(
            "dateFrom",
            encoded = true
        ) date: String,
        @Query("limit") limit: Int
    ): Deferred<Response<FlightsResponse>>
}
