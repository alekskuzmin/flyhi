package com.alekskuzmin.flyhi.core.remote


interface ServiceProvider {
    fun cancelAllRequests()

    val service: FlyHiService
}
