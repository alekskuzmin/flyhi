package com.alekskuzmin.flyhi.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alekskuzmin.flyhi.core.dispatcher.FlightsDispatcher
import com.alekskuzmin.flyhi.core.state.AppState
import com.alekskuzmin.flyhi.core.state.StateHolder
import com.alekskuzmin.flyhi.core.view.FlightsViewState

abstract class FlyHiViewModel(
    protected val stateHolder: StateHolder,
    protected val dispatcher: FlightsDispatcher
) :
    ViewModel() {

    fun getRenderedState(): LiveData<FlightsViewState> {
        return Transformations.map(stateHolder.getAppState()) { appState ->
            preRender(appState).also { flightsViewState: FlightsViewState? ->
                appState.error?.let { flightsViewState?.error = it }
            }
        }
    }

    abstract fun preRender(appState: AppState): FlightsViewState?

    fun retryLastAction() {
        dispatcher.retryLastAction()
    }

    fun resetAppState() {
        stateHolder.resetState()
    }

}
