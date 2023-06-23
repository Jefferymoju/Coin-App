package com.jefferymoju.coinapp.domain.use_case.get_coin

import com.jefferymoju.coinapp.common.Resource
import com.jefferymoju.coinapp.data.remote.dto.toCoin
import com.jefferymoju.coinapp.domain.model.Coin
import com.jefferymoju.coinapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    // we inject the coin repository
    private val repository: CoinRepository
) {
    //we override the operator function invoke so we can call the use case as if it was a function
    //then we return a flow because we want to emit multiple values over a period of time
    //the resource emits if it is success and then returns a list of coins
    // loading or error with the error message
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            // first thing we want to emit is the resource.loading so we receive that in our viewmodel and
            // display the progress bar
            emit(Resource.Loading<List<Coin>>())
            // we then have our list og coins that we get from our repository
            val coins = repository.getCoins().map { it.toCoin() }
            // if the request was successful
            emit(Resource.Success<List<Coin>>(coins))
        }catch (e:HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection"))
        }
    }
}