package com.example.cryptotracker.mainScreen.state

import com.example.cryptotracker.mainScreen.data.Crypto

data class CryptoUiState(
    val cryptoList: List<Crypto> = emptyList()
)
