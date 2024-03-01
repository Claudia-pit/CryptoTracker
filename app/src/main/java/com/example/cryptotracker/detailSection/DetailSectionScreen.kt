package com.example.cryptotracker.detailSection

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cryptotracker.R
import com.example.cryptotracker.toDoubleList
import com.example.cryptotracker.ui.theme.Dimens
import com.example.cryptotracker.ui.theme.Purple900
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.overlayingComponent
import com.patrykandpatrick.vico.compose.component.shape.dashedShape
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.entry.entryModelOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSection(navController: NavController, viewModel: CoinViewModel, itemId: String) {
    val coin = viewModel.uiState.collectAsState()
    val chart = coin.value.coin?.market_data?.sparkline_7d?.price?.toDoubleList() ?: emptyList()
    val chartEntryModel = entryModelOf(chart)
    val hyperlinktext = coin.value.coin?.links?.homepage?.get(0)
    val priceChangePercentage7d = coin.value.coin?.market_data?.price_change_percentage_7d
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, contentDescription = "Back button", tint = Color.White)

                    }
                },
                title = {
                    coin.value.coin?.let { coin ->
                        Text(
                            text = coin.name,
                            color = Color.White
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = Purple900
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.White)
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
                                model = coin.value.coin?.image?.large,
                                contentDescription = "crypto logo"
                            )

                        }
                    }
                    Row {
                        Column {
                            coin.value.coin?.let { coin ->
                                Text(
                                    text = coin.name,
                                    modifier = Modifier,
                                    fontSize = 18.sp
                                )
                            }
                            coin.value.coin?.let { coin ->
                                Text(
                                    text = coin.symbol,
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = Dimens.Regular),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "€ " + coin.value.coin?.market_data?.current_price?.eur.toString(),
                                modifier = Modifier,
                                fontSize = 18.sp
                            )
                            if (priceChangePercentage7d != null) {
                                Text(
                                    text = stringResource(R.string._7_days) + " " + priceChangePercentage7d + " %",
                                    modifier = Modifier,
                                    fontSize = 14.sp,
                                    color = if (priceChangePercentage7d == 0.0) {
                                        Color.DarkGray
                                    } else if (priceChangePercentage7d > 0.0) {
                                        Color.Green
                                    } else {
                                        Color.Red
                                    }
                                )
                            }
                        }
                    }

                }

            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dimens.Smallest,
                color = Purple900
            )
            val indicatorInnerComponent = shapeComponent(Shapes.pillShape, Color.Black)
            val indicatorCenterComponent = shapeComponent(Shapes.pillShape, Color.Black)
            val indicatorOuterComponent = shapeComponent(Shapes.pillShape, Color.Black)

            Chart(
                chart = lineChart(),
                model = chartEntryModel,
                modifier = Modifier.padding(horizontal = Dimens.Regular),
                startAxis = rememberStartAxis(label = textComponent { this.color = R.color.purple_900 }),
                bottomAxis = rememberBottomAxis(),
                marker = MarkerComponent(
                    textComponent {
                        this.color = R.color.purple_900
                    },
                    indicator = overlayingComponent(
                        outer = indicatorOuterComponent,
                        inner = overlayingComponent(
                            outer = indicatorCenterComponent,
                            inner = indicatorInnerComponent,
                            innerPaddingAll = Dimens.XSmall
                        ),
                        innerPaddingAll = Dimens.Small
                    ),
                    guideline = lineComponent(
                        color = MaterialTheme.colorScheme.onSurface.copy(.2f),
                        thickness = Dimens.Smallest,
                        shape = Shapes.dashedShape(shape = Shapes.pillShape, dashLength = Dimens.Small, gapLength = Dimens.XSmall)
                    )
                ),

                isZoomEnabled = true
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = Dimens.Smallest,
                color = Purple900
            )
            Text(
                text = stringResource(R.string.info),
                modifier = Modifier
                    .padding(start = Dimens.Regular, top = Dimens.Regular),
                color = Purple900,
                fontSize = 22.sp
            )

            Surface(
                modifier = Modifier
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(top = Dimens.Regular)
            ) {
                Column {
                    coin.value.coin?.description?.en?.let { coinDesc ->
                        Text(
                            text = coinDesc,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(start = Dimens.Regular, end = Dimens.Regular),
                            fontSize = 18.sp
                        )
                    }

                    val appendText = buildAnnotatedString {
                        if (hyperlinktext != null) {
                            pushStringAnnotation(
                                tag = "Website",
                                annotation = hyperlinktext
                            )
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        ) {
                            append(stringResource(R.string.official_website))
                        }

                    }

                    ClickableText(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(Dimens.Regular),
                        text = appendText,
                        onClick = {
                            appendText.getStringAnnotations(
                                start = it,
                                end = it
                            ).firstOrNull()?.let { annotation ->
                                if (annotation.tag == "Website") {
                                    Uri.parse(annotation.item).also {
                                        context.startActivity(
                                            Intent(Intent.ACTION_VIEW, it)
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    DetailSection(navController = NavController(LocalContext.current), viewModel = CoinViewModel(), itemId = "f alli")
}


