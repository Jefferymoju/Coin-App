package com.jefferymoju.coinapp.domain.model

// contains everything of the coinDto except those we dont need
//this is the data class we will use to display specific coin data in our list
// we want to use this so we need to find a way to transfrom coinDto to our coin
data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
