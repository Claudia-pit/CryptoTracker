package com.example.cryptotracker.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.R

@Composable
fun ErrorDialog(onRefresh: () -> Unit) {

    AlertDialog(
        onDismissRequest = { /* */ },
        confirmButton = {
            Button(onClick = {
            onRefresh()
        }) {
            Text(stringResource(R.string.try_again))
        }
        },
        icon = { Icon(Icons.Filled.Warning, contentDescription = stringResource(R.string.error), tint = Color.Red) },
        title = {Text(text = stringResource(R.string.network_error))},
        text ={ Text(text = stringResource(R.string.check_connection))},
        containerColor = Color.White
    )
}

@Preview
@Composable
fun ErrorPreview() {
    ErrorDialog() {

    }
}