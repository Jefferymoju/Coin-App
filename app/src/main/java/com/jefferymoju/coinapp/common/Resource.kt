package com.jefferymoju.coinapp.common

// we can put this class around any object which will be our network response
// it contains information about the data and potential error message
sealed class Resource <T>(val data: T? = null, val message: String? = null){
    // emit success if the data loads successfully
    class Success<T>(data:T): Resource<T>(data)
    // error if it failed
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    // Loading while it is still loading
    class Loading<T>(data: T? = null) : Resource<T>(data)
    // it will help for ui states
}