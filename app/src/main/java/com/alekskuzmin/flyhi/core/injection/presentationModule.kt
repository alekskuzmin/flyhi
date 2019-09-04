package com.alekskuzmin.flyhi.core.injection

import com.alekskuzmin.flyhi.dateselection.presentation.DateSelectionVm
import com.alekskuzmin.flyhi.flights.presentation.AvailableFlightsVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { DateSelectionVm(get(), get()) }
    viewModel { AvailableFlightsVm(get(), get()) }

}