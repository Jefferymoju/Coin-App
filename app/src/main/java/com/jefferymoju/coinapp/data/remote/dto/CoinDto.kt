package com.jefferymoju.coinapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jefferymoju.coinapp.domain.model.Coin

// we get this from our Api
data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

//function to map the transformation to our coin dataclass
//we can later call this function to convert a CoinDto to a normal Coin
fun CoinDto.toCoin(): Coin{
    return Coin(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}