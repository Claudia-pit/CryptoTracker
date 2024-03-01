package com.example.cryptotracker

import android.app.Activity
import android.view.Window
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
