package com.alekskuzmin.flyhi.core.injection

import com.alekskuzmin.flyhi.core.dispatcher.FlightsDispatcher
import com.alekskuzmin.flyhi.core.state.Reducer
import com.alekskuzmin.flyhi.core.state.StateHolder
import org.koin.dsl.module

val stateModule = module {

    single { FlightsDispatcher(get()) }
    single { Reducer() }
    single { StateHolder(get(), get()) }
}
