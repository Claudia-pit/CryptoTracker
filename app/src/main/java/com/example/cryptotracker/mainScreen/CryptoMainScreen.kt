package com.example.cryptotracker.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cryptotracker.R
import com.example.cryptotracker.detailSection.CoinViewModel
import com.example.cryptotracker.mainScreen.data.Crypto
import com.example.cryptotracker.ui.theme.Dimens

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: CryptoViewModel,
    coinViewModel: CoinViewModel,
    action: (String) -> Unit
) {

    val crypto by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = stringResource(R.string.your_wallet),
                modifier = Modifier
                    .padding(horizontal = Dimens.Regular)
                    .padding(top = Dimens.Medium),
                color = Color.DarkGray,
                fontSize = 20.sp
            )
            Text(
                text = "€123,76",
                modifier = Modifier
                    .padding(horizontal = Dimens.Regular)
                    .padding(top = Dimens.Small, bottom = Dimens.Small),
                color = Color.Black,
                fontSize = 28.sp
            )
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = Dimens.radius, topEnd = Dimens.radius),
            color = Color.White,
            shadowElevation = Dimens.XSmall
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                item {
                    Text(
                        text = stringResource(R.string.top_10_crypto),
                        modifier = Modifier
                            .padding(start = Dimens.Regular, top = Dimens.Regular),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
                items(crypto.cryptoList.size) { cryptos ->
                    CryptoItem(crypto = crypto.cryptoList[cryptos]) { itemId ->
                        coinViewModel.getCoin(itemId)
                        navController.navigate("detail/${itemId}")

                    }
                }

            }


        }

    }
}


@Composable
fun CryptoItem(
    crypto: Crypto,
    action: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .clickable { action(crypto.id) }
        ) {
            Surface(
                color = Color.White,
            ) {
                Box(
                    modifier = Modifier
                        .padding(Dimens.Regular)
                        .height(Dimens.iconDimensRegular)
                        .width(Dimens.iconDimensRegular)
                        .clip(shape = RoundedCornerShape(Dimens.Small))

                ) {
                    AsyncImage(
                        model = crypto.image,
                        contentDescription = "crypto logo"
                    )
                }
            }
            Row {
                Column {
                    Text(
                        text = crypto.name,
                        modifier = Modifier,
                        fontSize = 18.sp
                    )
                    Text(
                        text = crypto.symbol,
                        modifier = Modifier,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = Dimens.Regular),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = crypto.currentPrice.toString(),
                        modifier = Modifier,
                        fontSize = 18.sp
                    )
                    Text(
                        text = crypto.priceChangePercentage24h.toString(),
                        modifier = Modifier,
                        fontSize = 14.sp,
                        color = if (crypto.priceChangePercentage24h == 0.0) {
                            Color.DarkGray
                        } else if (crypto.priceChangePercentage24h > 0) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dimens.Smallest,
                color = Color.DarkGray
            )

        }
    }


}
