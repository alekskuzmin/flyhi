package com.alekskuzmin.flyhi.core.remote.model

import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("price")
    val price: Int,
    var currency: String?,
    @SerializedName("cityFrom")
    val cityFrom: String,
    @SerializedName("cityTo")
    val cityTo: String,
    @SerializedName("mapIdto")
    val mapIdto: String,
    @SerializedName("deep_link")
    val deep_link: String?
)