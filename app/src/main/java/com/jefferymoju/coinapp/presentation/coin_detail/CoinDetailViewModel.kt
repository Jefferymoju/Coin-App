package com.jefferymoju.coinapp.presentation.coin_detail.component

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefferymoju.coinapp.common.Constants
import com.jefferymoju.coinapp.common.Resource
import com.jefferymoju.coinapp.domain.use_case.get_coin.GetCoinsUseCase
import com.jefferymoju.coinapp.domain.use_case.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel  @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    // we create this val for the coinId
    // it is a bundle that contains information for a saved state
    // we can use it to restore our app from process
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // state that cointains the coinListstate for composable
    // we create a private and public version of the state because we don't want to be able to modify
    // the content of the state in our composable
    // thats why we can only access the mutable state in our viewmodel
    // and we expose the immutable to our composables

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    // we then execute the getCoins function
    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    // function that calls the getcoin use case and put the data in our state object to display in our UI
    private fun getCoin(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                // when the result is success get the data
                is Resource.Success -> {
                  _state.value = CoinDetailState(coin = result.data )
                }
                //. when it is an error , show the error message
                is Resource.Error -> {
                   _state.value = CoinDetailState(error = result.message ?:
                   "An unexpected error occured")
                }
                // loading
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
            // since using onEach is a Flow we have to launch it in a coroutine
        }.launchIn(viewModelScope)
    }
}