package com.jefferymoju.coinapp.domain.repository

import com.jefferymoju.coinapp.data.remote.dto.CoinDetailDto
import com.jefferymoju.coinapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}