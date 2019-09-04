package com.alekskuzmin.flyhi.core.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.alekskuzmin.flyhi.core.dispatcher.FlightsDispatcher

class StateHolder(private val dispatcher: FlightsDispatcher, private val reducer: Reducer) {

    private val appState = MediatorLiveData<AppState>().also {
        it.value = AppState.getInitialState()
    }

    fun getAppState(): LiveData<AppState> {
        return appState
    }

    init {
        appState.addSource(Transformations.switchMap(dispatcher.nextAction) {
            reducer.reduce(
                appState,
                dispatcher.dispatchedActionResult(it)
            )
        }) {
            appState.value = it
        }
    }

    fun resetState() {
        appState.value = AppState.getInitialState()
    }

    fun isUninitialized() =
        appState.value == AppState.getInitialState()

}
