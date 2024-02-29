package com.example.cryptotracker.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.mainScreen.data.Crypto
import com.example.cryptotracker.mainScreen.state.CryptoUiState
import com.example.cryptotracker.net.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val apiServices = ApiClient.apiService

    private val _uiState = MutableStateFlow(CryptoUiState())
    val uiState: StateFlow<CryptoUiState>
        get() = _uiState

    init {
        getCrypto()
    }


    private fun getCrypto() {
        viewModelScope.launch {
            val response = apiServices.getCoinsList()
            if (response.isNotEmpty()) {
                setupCrypto(response.map { it.toDomain() })
            } else {
                Log.e("Viewmodel", "Error")
            }
        }
    }

    private fun setupCrypto(crypto: List<Crypto>) {
        _uiState.update { currentState ->
            currentState.copy(
                cryptoList = crypto
            )
        }

    }
}