package com.example.cryptotracker.detailSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.detailSection.data.SingleCoin
import com.example.cryptotracker.detailSection.state.CoinUiState
import com.example.cryptotracker.net.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val apiServices = ApiClient.apiService

    private val _uiState = MutableStateFlow(CoinUiState())
    val uiState: StateFlow<CoinUiState>
        get() = _uiState


    fun getCoin(id: String) {
        viewModelScope.launch {
            val response = apiServices.getCoinDetails(id = id)
            setupCoin(response.toDomain())
        }

    }

    private fun setupCoin(coin: SingleCoin?) {
        _uiState.update { currentState ->
            currentState.copy(
                coin = coin
            )
        }
    }


}

