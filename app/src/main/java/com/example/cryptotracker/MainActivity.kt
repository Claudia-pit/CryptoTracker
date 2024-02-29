package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.cryptotracker.detailSection.CoinViewModel
import com.example.cryptotracker.mainScreen.CryptoViewModel
import com.example.cryptotracker.mainScreen.ErrorDialog
import com.example.cryptotracker.net.checkForInternet


class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkForInternet(this)) {
            generateView()
        } else {
            setContent {
                ErrorDialog {
                    if (checkForInternet(this)) {
                        generateView()
                    }
                }
            }
        }
    }

    private fun generateView() {
        val cryptoViewModel: CryptoViewModel by viewModels()
        val coinViewModel: CoinViewModel by viewModels()
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                updateStatusBarColor(ContextCompat.getColor(this, R.color.white))
                changeStatusBarIconColor(true)
                AppNavigation(cryptoViewModel, coinViewModel)
            }
        }
    }
}






