package com.example.cryptotracker

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptotracker.detailSection.CoinViewModel
import com.example.cryptotracker.detailSection.DetailSection
import com.example.cryptotracker.mainScreen.CryptoViewModel
import com.example.cryptotracker.mainScreen.MainScreen

@Composable
fun AppNavigation(cryptoViewModel: CryptoViewModel, coinViewModel: CoinViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {

        composable("main") {
            MainScreen(navController = navController, viewModel = cryptoViewModel, coinViewModel = coinViewModel) {}
        }

        composable("detail/{item}") { backStackEntry ->
            val item = backStackEntry.arguments?.getString("item") ?: ""
            DetailSection(navController = navController, viewModel = coinViewModel, itemId = item)

        }
    }


}