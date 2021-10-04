package com.grando.pokemonapp.framework.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

fun <T> request(requestBlock: suspend () -> T): LiveData<State<T>> {
    return liveData {
        emit(State.LoadingState)
        try {
            emit(State.DataState(requestBlock()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(State.ErrorState(e))
        }
    }
}