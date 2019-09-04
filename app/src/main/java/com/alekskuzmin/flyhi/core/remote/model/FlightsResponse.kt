package com.alekskuzmin.flyhi.core.remote.model

import com.google.gson.annotations.SerializedName

class FlightsResponse(
    @SerializedName("currency")
    var currency: String,
    @SerializedName("data")
    val data: List<Flight>? = null
)