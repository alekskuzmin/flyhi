package com.alekskuzmin.flyhi.core.injection

import com.alekskuzmin.flyhi.core.remote.FlightsRepository
import com.alekskuzmin.flyhi.core.remote.ServiceProvider
import com.alekskuzmin.flyhi.core.remote.ServiceProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import org.koin.dsl.module

val remoteModule = module {

    single {
        GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create() as Gson
    }
    single { ServiceProviderImpl(get(), get()) as ServiceProvider }
    single { FlightsRepository(get()) }
    single { Dispatcher() }

}