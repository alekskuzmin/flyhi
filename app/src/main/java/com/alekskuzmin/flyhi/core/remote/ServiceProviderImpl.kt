package com.alekskuzmin.flyhi.core.remote

import com.alekskuzmin.flyhi.BuildConfig
import com.google.gson.Gson
import okhttp3.Dispatcher

class ServiceProviderImpl(
    private val gson: Gson,
    private val dispatcher: Dispatcher
) : ServiceProvider {

    override fun cancelAllRequests() {
        dispatcher.cancelAll()
    }

    private val flyHiService: FlyHiService by lazy {
        ServiceFactory.createService(
            gson,
            BuildConfig.BASE_URL,
            dispatcher
        )
    }

    override val service: FlyHiService
        get() = flyHiService
}