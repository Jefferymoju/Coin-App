package com.jefferymoju.coinapp.data.remote

import com.jefferymoju.coinapp.data.remote.dto.CoinDetailDto
import com.jefferymoju.coinapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    // function to get coins
    @GET("/v1/coins")
    // get the coins and return the list of coindto
    suspend fun getCoins(): List<CoinDto>

    // function to get data of a specific coin
    @GET("/v1/coins/{coinId}")
    // get the coinbyud and return the coindetaildto
    suspend fun getCoinById(@Path("coinId") coinId: String):CoinDetailDto
}