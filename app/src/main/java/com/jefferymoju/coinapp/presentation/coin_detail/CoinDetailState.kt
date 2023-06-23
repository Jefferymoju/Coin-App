package com.jefferymoju.coinapp.presentation.coin_detail.component

import com.jefferymoju.coinapp.domain.model.Coin
import com.jefferymoju.coinapp.domain.model.CoinDetail

// coin detail state for composable
data class CoinDetailState(
    // boolean for loading
    val isLoading: Boolean = false,
    // val for coin and we pass the CoinDetail to it
    val coin: CoinDetail? = null,
    //on error
    val error: String = ""
)
