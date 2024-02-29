package com.example.cryptotracker.net

import com.example.cryptotracker.detailSection.data.CoinInfoDTO
import com.example.cryptotracker.mainScreen.data.CryptoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v3/coins/markets?vs_currency=eur&per_page=10")
    suspend fun getCoinsList(): List<CryptoDTO>

    @GET("v3/coins/{id}?sparkline=true")
    suspend fun getCoinDetails(@Path("id") id: String): CoinInfoDTO

}