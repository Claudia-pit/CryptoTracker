package com.example.cryptotracker.mainScreen.data

import com.google.gson.annotations.SerializedName

data class CryptoDTO(
    @SerializedName("ath")val ath: Double,
    @SerializedName("ath_change_percentage")val athChangePercentage: Double,
    @SerializedName("ath_date")val athDate: String,
    @SerializedName("atl")val atl: Double,
    @SerializedName("atl_change_percentage")val atlChangePercentage: Double,
    @SerializedName("atl_date")val atlDate: String,
    @SerializedName("circulating_supply")val circulatingSupply: Double,
    @SerializedName("current_price")val currentPrice: Double,
    @SerializedName("fully_diluted_valuation")val fullyDilutedValuation: Long,
    @SerializedName("high_24h")val high24h: Double,
    @SerializedName("id")val id: String,
    @SerializedName("image")val image: String,
    @SerializedName("last_updated")val lastUpdated: String,
    @SerializedName("low_24h")val low24h: Double,
    @SerializedName("market_cap")val marketCap: Long,
    @SerializedName("market_cap_change_24h")val marketCapChange24h: String,
    @SerializedName("market_cap_change_percentage_24h")val marketCapChangePercentage24h: Double,
    @SerializedName("market_cap_rank")val marketCapRank: Int,
    @SerializedName("max_supply")val maxSupply: Double,
    @SerializedName("name")val name: String,
    @SerializedName("price_change_24h")val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")val priceChangePercentage24h: Double,
    @SerializedName("roi")val roi: Any,
    @SerializedName("symbol")val symbol: String,
    @SerializedName("total_supply")val totalSupply: Double,
    @SerializedName("total_volume")val totalVolume: Long
) {
    fun toDomain(): Crypto {
        return Crypto(
            ath = ath,
            athChangePercentage = athChangePercentage,
            athDate = athDate,
            atl = atl,
            atlChangePercentage = atlChangePercentage,
            atlDate = atlDate,
            circulatingSupply = circulatingSupply,
            currentPrice = currentPrice,
            fullyDilutedValuation = fullyDilutedValuation,
            high24h = high24h,
            id = id,
            image = image,
            lastUpdated = lastUpdated,
            low24h = low24h,
            marketCap = marketCap,
            marketCapChange24h = marketCapChange24h,
            marketCapChangePercentage24h = marketCapChangePercentage24h,
            marketCapRank = marketCapRank,
            maxSupply = maxSupply,
            name = name,
            priceChange24h = priceChange24h,
            priceChangePercentage24h = priceChangePercentage24h,
            roi = roi,
            symbol = symbol,
            totalSupply = totalSupply,
            totalVolume = totalVolume
        )

    }
}