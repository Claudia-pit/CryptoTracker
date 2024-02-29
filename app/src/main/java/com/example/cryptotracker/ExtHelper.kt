package com.example.cryptotracker

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import com.patrykandpatrick.vico.core.entry.FloatEntry

fun List<Double>.toDoubleList(): List<FloatEntry> {
    return this.mapIndexed { index, value -> FloatEntry(index.toFloat(), value.toFloat()) }
}

fun Activity.updateStatusBarColor(color: Int) {
    val window: Window = window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
}

fun Activity.changeStatusBarIconColor(toWhite: Boolean) {
    if (toWhite) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, // value
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS // mask
            )

        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0, // value
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS // mask
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
}