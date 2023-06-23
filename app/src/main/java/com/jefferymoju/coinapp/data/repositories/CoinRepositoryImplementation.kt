package com.jefferymoju.coinapp.data.repositories

import com.jefferymoju.coinapp.data.remote.CoinPaprikaApi
import com.jefferymoju.coinapp.data.remote.dto.CoinDetailDto
import com.jefferymoju.coinapp.data.remote.dto.CoinDto
import com.jefferymoju.coinapp.domain.repository.CoinRepository
import javax.inject.Inject

// we inject dependency
class CoinRepositoryImplementation @Inject constructor(
    // create a val and pass the coinpaprika api to it
    private val api: CoinPaprikaApi
    // return our coinrepository
) : CoinRepository {
    // and then implement the functions
    override suspend fun getCoins(): List<CoinDto> {
        // and return our api.getcoins
       return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}