package com.alekskuzmin.flyhi.core.remote

sealed class NetworkResult<TYPE> {
    data class Success<TYPE>(val data: TYPE) : NetworkResult<TYPE>()
    data class Failure<TYPE>(val error: String) : NetworkResult<TYPE>() {
        override fun toString(): String {
            return error
        }
    }
}