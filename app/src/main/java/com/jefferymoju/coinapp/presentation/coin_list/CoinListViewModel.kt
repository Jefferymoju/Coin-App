package com.jefferymoju.coinapp.presentation.coin_list.component

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefferymoju.coinapp.common.Resource
import com.jefferymoju.coinapp.domain.use_case.get_coin.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel  @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    // state that cointains the coinListstate for composable
    // we create a private and public version of the state because we don't want to be able to modify
    // the content of the state in our composable
    // thats why we can only access the mutable state in our viewmodel
    // and we expose the immutable to our composables

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    // we then execute the getCoins function
    init {
        getCoins()
    }

    // function that calls the getcoins use case and put the data in our state object to display in our UI
    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                // when the result is success get the data
                is Resource.Success -> {
                  _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                //. when it is an error , show the error message
                is Resource.Error -> {
                   _state.value = CoinListState(error = result.message ?:
                   "An unexpected error occured")
                }
                // loading
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
            // since using onEach is a Flow we have to launch it in a coroutine
        }.launchIn(viewModelScope)
    }
}