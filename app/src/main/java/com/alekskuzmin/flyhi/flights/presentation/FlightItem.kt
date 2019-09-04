package com.alekskuzmin.flyhi.flights.presentation

data class FlightItem(
    val cityTo: String,
    val description: String,
    val imagePath: String,
    val bookingUrl: String?
)
