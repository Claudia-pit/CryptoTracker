package com.example.cryptotracker.detailSection

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.detailSection.data.SingleCoin
import com.example.cryptotracker.detailSection.state.CoinUiState
import com.example.cryptotracker.net.ApiClient
import com.example.cryptotracker.net.onExceptionOccurred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val apiServices = ApiClient.apiService

    private val _uiState = MutableStateFlow(CoinUiState())
    val uiState: StateFlow<CoinUiState>
        get() = _uiState


    fun getCoin(id: String, context: Context) {
        viewModelScope.launch {
            var coin: SingleCoin? = null
            try {
                val response = apiServices.getCoinDetails(id = id)
                coin = response.toDomain()
            } catch (e: Exception) {
                onExceptionOccurred(e)
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
            setupCoin(coin)
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

